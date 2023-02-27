# LunarLander Project
+3 month long Simulation project which lands lunar lander with 17 thrusters and 4 hitboxes from specific coordinates to the origin of the world.   
First run test engine before python script.   
To run engine without step logic (made for the learning algorithm) set step attrb = 1 and remove step-- from EngineManager. Otherwise you will see black screen meaning   engine waits for step() call.   
For manual thrusting controls which are IMPOSSIBLE to land with can be learned from TestGame class.   
There are 36 input combinations some which are not defined in the learning algorithm.  
For manual control you MUST check Test class vector summing thrusting method AND check position vectors of thrusters relative to centerEntity for you to know which   thruster you will ignite.  
Controls are not toggle, and you can use many of them simulataneously. Learner ignites single thruster (or none) each step.  
Lunar Lander texture is undefined i know. Texture mapping in 3D engine is not enough for definining more complex textures, I could not found a way to implement reading   .mtl files, in my own engine.  
Physics engine is implemented for only this project. Collisions will not be accurate for a different entity.  
![image](https://user-images.githubusercontent.com/92366936/221451725-44fe2458-5b27-4274-8d9f-bb80b6141950.png)  
