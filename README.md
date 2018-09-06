## DPI calculator with mutliplatform codebase in Kotlin

This project showcases the ability to write multiplatform projects in Kotlin. It's a DPI calculator app inspired by [this project](http://jennift.com/dpical.html).
It can be built as an Android app or as a Firefox browser extension.


### Prerequisites

* Android dev environment
* [web-ext](https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/Getting_started_with_web-ext) tool for testing Web extension
    - `npm install --global web-ext`


### Building and Running

Full build can be performed using typical `./gradlew build` command. Aside of that different parts can be run separately


#### Android

`./gradlew :dpi-android:installDebug` to build and install the app to connected device or emulator


#### Browser extension

* `./gradlew :dpi-js:runDceKotlinJs` to build the browser extension
* `web-ext run` (executed in `dpi-js/` folder) to run Firefox instance with our extension installed


Alternatively you can use `--continuous` build flag to watch for changes and rebuild the extension automatically:

`./gradlew :dpi-js:runDceKotlinJs --continuous`
