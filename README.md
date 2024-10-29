# How to Run
Run `gradle clean build run`

# Design Patterns
## Strategy Pattern

Files included: Located in the src/main/java/pacman/model/entity/dynamic/ghost/strategy package
- GhostStrategy.java
- BinkyStrategy.java
- ClydeStrategy.java
- InkyStrategy.java
- PinkyStrategy.java

Participants:
- Strategy: GhostStrategy
- ConcreteStrategy: BinkyStrategy, ClydeStrategy, InkyStrategy, PinkyStrategy
- Context: Ghost


## Decorator Pattern

Files included: Located in the src/main/java/pacman/model/entity/staticentity/CollectableDecorator package
- CollectableDecorator.java
- PointDoubleDecorator.java
- Collectable.java
- Pellet.java
- PowerPellet.java
- Ghost.java

Participants:
- Component: Collectable
- ConcreteComponent: Pellet, PowerPellet, Ghost
- Decorator: CollectableDecorator
- ConcreteDecorator: PointDoubleDecorator


## State Pattern

Files included: src/main/java/pacman/model/entity/dynamic/ghost package
- GhostState.java
- ChaseState.java
- FrightenedState.java
- ScatterState.java
- EatenState.java
- GhostImpl.java

Participants:
- Context: GhostImpl
- State: GhostState
- ConcreteState: ChaseState, FrightenedState, ScatterState, EatenState

## Observer Pattern

Files included: Located in src/main/java/pacman/model/entity/dynamic/ghost/observer package
- BlinkyPositionObserver.java
- BlinkyPositionSubject.java
- GhostStrategy.java
- Ghost.java

Participants:
- Subject: BlinkyPositionSubject
- ConcreteSubject: Ghost, GhostImpl
- Observer: BlinkyPositionObserver
- ConcreteObserver: GhostStrategy, BinkyStrategy, ClydeStrategy, InkyStrategy, PinkyStrategy
