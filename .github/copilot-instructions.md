# Project Guidelines

## Overview

RSS Reader is a zero-dependency Java 11+ library for parsing RSS and Atom feeds, published to Maven Central as `com.apptasticsoftware:rssreader`. It uses JPMS (`module com.apptasticsoftware.rssreader`) and StAX for XML parsing.

## Build and Test

```sh
./gradlew build          # compile + test + JaCoCo coverage
./gradlew test           # tests only
./gradlew javadoc        # generate API docs
./gradlew jacocoTestReport  # coverage report
```

Java 11 source/target compatibility. No runtime dependencies — only test-scoped: JUnit 5, AssertJ, Mockito, EqualsVerifier.

## Key Files

| File | Purpose |
|------|---------|
| `src/main/java/module-info.java` | JPMS exports — update when adding packages |
| `src/main/java/.../AbstractRssReader.java` | Base parser: StAX, HTTP, extension registry |
| `src/main/java/.../FeedReader.java` | Unified reader registering ALL modules |
| `src/main/java/.../FeedExtensionRegistry.java` | Tag → setter mapping API |
| `src/main/java/.../util/Mapper.java` | Type conversion utilities |
| `src/main/java/.../XMLInputFactorySecurity.java` | XXE protection hardening |

## Architecture

### Core class hierarchy

- `AbstractRssReader<C extends Channel, I extends Item>` — generic base handling StAX parsing, HTTP, filters, timeouts
- `RssReader` — basic reader (no extensions)
- `FeedReader` — unified reader registering ALL module extensions via composed `FeedChannel`/`FeedItem`
- `Channel` / `Item` — public interfaces; `ChannelImpl` / `ItemImpl` — internal implementations

### Module extension pattern

Each namespace module lives in `src/main/java/com/apptasticsoftware/rssreader/module/<name>/` and follows this structure:

| File | Purpose |
|------|---------|
| `XyzChannel` | Interface extending `Channel` + `XyzChannelData` |
| `XyzItem` | Interface extending `Item` + `XyzItemData` |
| `XyzChannelData` / `XyzItemData` | Data interfaces with default getter/setter methods delegating to `getXyzItemData()` |
| `XyzExtensions` | Static `register()` method wiring XML tags → setters via `FeedExtensionRegistry` |
| `XyzFeedReader` | Standalone reader extending `AbstractRssReader` |
| `internal/XyzChannelImpl` | Extends `ChannelImpl`, composes `XyzChannelDataImpl` |
| `internal/XyzItemImpl` | Extends `ItemImpl`, composes `XyzItemDataImpl` |
| `internal/XyzChannelDataImpl` / `XyzItemDataImpl` | Plain data holders with equals/hashCode |

Modules: atom, content, dc, georss, itunes, mediarss, opensearch, podcast, psc, slash, spotify, wfw, youtube.

When adding a new module, update these three locations:
1. `module-info.java` — add `exports` for the new package
2. `FeedReader.registerChannelTags()` — call `XyzExtensions.register(registry)`
3. `FeedChannel`/`FeedItem` interfaces and their internal impls — compose new data interfaces

### Extension registration API

`FeedExtensionRegistry` provides these registration methods in `XyzExtensions.register()`:

```java
// Map XML tag text content to a setter
registry.addChannelExtension("ns:tag", XyzChannel::setSomeField);
registry.addItemExtension("ns:tag", XyzItem::setSomeField);

// Map XML attribute value to a setter
registry.addItemExtension("ns:tag", "attributeName", XyzItem::setSomeField);

// Callback on tag start (e.g., to initialize lists)
registry.addOnItemTag("ns:tag", item -> { /* on START_ELEMENT */ });
```

Use `Mapper` for non-String types: `(item, value) -> mapInteger(value, item::setCount)`

## Conventions

- **Getters return `Optional<T>`** for nullable fields; setters accept direct types
- **Lists** for multi-valued fields (categories, enclosures)
- **Internal packages** (`internal/`) are not exported — keep implementation details there
- **Type conversions** use `Mapper` utilities (`mapBoolean`, `mapInteger`, `mapLong`, `mapDouble`)
- **Deprecation**: Use `@Deprecated(since="X.Y.Z", forRemoval=true)` with migration guidance
- **XML security**: `XMLInputFactorySecurity` hardens the StAX parser against XXE — never bypass it

## Testing

- **JUnit 5** with **AssertJ** assertions (including `hasValue()` for Optional assertions)
- **Parameterized tests** via `@ParameterizedTest` + `@MethodSource` — test both the standalone `XyzFeedReader` AND the unified `FeedReader`
- **EqualsVerifier** for equals/hashCode contracts on all `*Impl` and `*DataImpl` classes — ignore `"defaultComparator"` and `"dateTimeParser"` fields in `*ItemImpl`/`*ChannelImpl`
- **Mockito** for HTTP response mocking
- **Fixtures** in `src/test/resources/` — module-specific XML in `module/<name>/`
- Load fixtures via `getClass().getClassLoader().getResourceAsStream(path)`

## Pitfalls

- **Namespace awareness is OFF** (`IS_NAMESPACE_AWARE=false`) — XML tags use their prefixed form (e.g., `slash:comments`, not just `comments`)
- **`IS_COALESCING=true`** — adjacent text/CDATA are merged; don't rely on individual text events
- **Data interface delegation pattern**: `XyzItemData.getXyzItemData()` returns `this` in the data impl but delegates in the composite — don't break this chain
- **Module test fixture naming**: Place XML files in `src/test/resources/module/<name>/` matching the module name
