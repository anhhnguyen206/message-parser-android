# Message parser - Atlassian Android Test

### Architecture
- Model View Presenter
    * Model: are interactors.  These interactors are the bridge between the presenters to the data sources (local databases, Restful services ...)
    * Presenter: in charge of controlling the views, decide what to show, when to show it.
    * View: all activities, fragments, custom views are treated as views only.  They should solely do things that matter in the UI (animation...)
- Dagger 1.2 to inject dependencies: http://square.github.io/dagger/
- RxAndroid to performs asyncrhonous operation and reduce boilerplate code (https://github.com/ReactiveX/RxAndroid)
- Interactors: are business-contained service that the presenters will use.

### Unit Tests
- Mockito: use to mock objects and controll its behaviors.
- Junit 4
- The tests are mainly focused on testing the interactors, presenters, and the parsing logic.
- Run ./gradlew test --continue to execute the tests

### Known issues:
- Any classes that use Dagger injection will suffer from lowering code-coverage, because at compile time Dagger will generate an inner class inside those classes to perform the injection.  And those classes of course are not tested.  Need to figure out a way to exclude those classes from coverage data.
- Haven't done any UI testing.  Will do later on, probably using Espresso.