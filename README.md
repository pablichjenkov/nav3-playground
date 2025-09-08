### Nav3 Playground

#### Module Architecture
This project inter-modular dependency uses a Diamond Architecture.

<img width="700" alt="Diamond-Arch" src="https://github.com/user-attachments/assets/8d2b4e63-ccdf-4223-b11f-8f9a61affa3e" />

In such architecture, feature modules do not depend directly on each other. They all depend on a **common** module which contains the public APIs each of this module exposes to its clients. On top of the diamond, you see the integrator module, this is the app module. It basically acts like a coordiantor between all the modules.

#### Navigation
This project uses google compose only **nav3** navigation library. In this architecture each module is responsible for its internal navigation. Some modules contain an internal NavDisplay as an example of **nested NavDisplays**.
<BR/>
The integrator module on the top of the diamond, this one is in charge of the **General App/Module Navigation**. This top module is also in charge of tasks like **App Startup**, **Notifications Routing**, **DeepLink Routing** and such a global application stuff.

#### DI
The DI framework of choice is Metro, go check the [Metro Repo](https://github.com/ZacSweers/metro), highly recommended.
<BR/>
In the diamond architecture, each module exposes the public abstract API in common. Metro will bind the actual implementations from the feature modules or the app module.

#### Demo
<image width="330" src="https://github.com/user-attachments/assets/69d541c5-5efe-4e5f-bd9f-1e5ee295eab2"/>
