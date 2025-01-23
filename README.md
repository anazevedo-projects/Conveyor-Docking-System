This project simulates a logistics distribution conveyor system with multiple docks and dynamic package routing. The system operates with three docks: Dock 1, Dock 2, and Dock End, where packages are delivered based on predefined configurations. The user can set which package types should be delivered to Dock 1 and Dock 2, while all other packages will go to Dock End.

Key Features:

- Dynamic Dock Configuration: Users can configure which package types go to each dock, with the option to change the configuration during operation.
- Dock Availability Monitoring: Docks can be closed or blocked, and the system automatically reroutes packages to available docks. Flashing LEDs indicate blocked or full docks.
- Emergency Stop/Resume: An emergency stop feature is included, with an emergency flashing LED pattern and system pause.
- System State and Statistics: The system provides real-time statistics on package deliveries, including the number of packages processed at each dock, visible through the user interface.
- Idle and Blocked States: The system handles blocked docks and enters a "full blocked state" when all docks are closed, signaling the issue through LED flashing.

The user interface allows for monitoring and controlling the system’s state, providing visibility into the number of packages processed at each dock and handling operations with an intuitive design.
