# PedroLibrary

## Installation guide
Add this to the `repositories` in your `build.gradle` file:

```groovy
repositories {
    ivy {
        url = "https://github.com/Pedro270707"
        patternLayout {
            artifact "[organisation]/releases/download/v[revision]/[module]-[revision].jar"
            m2compatible = true
        }
        metadataSources { artifact() }
    }
}
```

You can then implement the mod in the `dependencies` in your `build.gradle` file:

```groovy
dependencies {
    modImplementation "pedrolibrary-bta:pedrolibrary:1.0.2_01"
}
```

After that, reload dependencies and you're set.