# Angry Bird 
					ANGRY BIRD
How to Run:
Step 1: Download the Project from GitHub
        Go to the GitHub repository: https://github.com/karan23273/Project_Angry.git
        Click the Code button and select Download ZIP.
        Or download from the google classroom.
        Once downloaded, unzip or extract the contents to a folder on your computer.
Step 2: Open the Project in IntelliJ IDEA
        Launch IntelliJ IDEA and navigate to File > Open.
        Select the folder where you extracted the project files. IntelliJ will recognize it as a Gradle project.
        Choose Open as Project and allow the IDE to import the necessary dependencies.
        Go to Run > Edit Configurations and click the + button to add a new Application configuration.
        Set the Main class to LWJGL3Launcher or LWJGL2Launcher, depending on your preference. You can find the correct path under Project_Angry-main\lwjgl3\src\main\java\com\Angry_Bird\lwjgl3.
Step 3: Run the Project Using Gradle
        Open the Terminal in IntelliJ or use your system’s command prompt.
        Change the directory to the project folder using the cd command.
        Run the project with the command gradlew.bat lwjgl2:run (or gradlew.bat lwjgl3:run)


Project Structure
        Launch Class:
                This is the foundational class for the entire project, responsible for initializing critical components like the viewport, camera, font, and other essentials.
                Acts as the entry point and backbone, managing the transition to the LoadScreen by passing this as a reference.
        Screen Package:
                This package contains all the primary screens of the project. Each screen class implements the Screen interface to ensure standardized screen management.
                LoadScreen: Displays a loading structure and waits for essential assets to load, providing visual feedback during loading.
                MainMenuScreen: The central hub for navigation, allowing access to settings, shop, exit, and login screens. It supports bidirectional navigation, enabling users to move easily                                      between different screens.
                ShopScreen: Allows players to purchase items like coins and birds, with a well-defined structure for transactions.
                SettingsScreen: Manages user preferences for sound and music toggling. It also provides options for saving or erasing data by updating the database.
                LoginScreen: Handles user login, updating the user database to track progress and maintain session data.
                LevelScreen: Displays all available levels, allowing users to select and start levels.
                LevelFailedScreen: Shown when the player has no birds left, but pigs remain on the field, indicating a level failure.
                LevelPassedScreen: Displays upon successfully completing a level when all pigs are eliminated.
        Button Package:
                Contains a versatile button class that provides a structure for various button types used across the project.
                Buttons can be instantiated with textures and support multiple states and actions, such as drawing, toggling, and triggering events for simple button presses.
        Birds Package:
                Defines an interface for different bird types, ensuring that each bird class implements the same methods for consistent functionality.
        Pig Package:
                Contains an interface for different pig types, structuring the classes to implement shared methods for uniform behavior across pig variants.
        Blocks Package:
                Contains classes for creating blocks of different types and sizes, used for constructing level structures.
                Includes  class for building structured frames to enhance level design.
OOPS Concept
        Encapsulation: All the necessary attributes instantiated inside project are kept private so that no external class could’ve access these attributes these access modifiers enhance                                  encapsulation to access the attributes of launch there is accessor and mutator. 
        Inheritance: Game library is extended to implement necessary game component of the game this helps in code reusability.
        Abstraction: Abstractions is achieved using button class which create buttons required in different classes which hides implementation of buttons to all the classes only main functionality                      is visible to classes.
        Polymorphism: Polymorphism is achieved in almost every class like launch class which override methods to achieve method overriding(runtime polymorphism).
         

Project Contributions
        Karan Singh (2023273)
                Core Code Implementation: Designed and developed the entire code structure, creating all essential classes and their interactions to ensure cohesive functionality within the                                                  project.
                Project Ideology and Structure: Defined the overall architecture and layout of the project, establishing foundational components and design patterns to optimize performance and                                                     maintainability.
                GitHub Setup and Management: Initialized the GitHub repository, organized the project structure, and maintained version control to facilitate collaborative development.
                Testing and Debugging: Conducted thorough testing and debugging, addressing issues and refining code to ensure smooth operation across all project features and components.
        Anshul Rawat (2023104)
                Use Case Diagram Creation: Designed and developed the project's use case diagrams to illustrate core functionalities, user interactions, and system boundaries.
                UML Diagram Design: Created and refined UML diagrams to map out class structures, object relationships, and component dependencies, providing a comprehensive visual representation                                      of the project architecture.
                Researched Images: Searched the images of birds,pigs,obstacles and the background image used in the game’s display and code refinement.


Git hub Repository: https://github.com/karan23273/Project_Angry.git




A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.
- `lwjgl2`: Legacy desktop platform using LWJGL2.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl2:jar`: builds application's runnable jar, which can be found at `lwjgl2/build/libs`.
- `lwjgl2:run`: starts the application.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
