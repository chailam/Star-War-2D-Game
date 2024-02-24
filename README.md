# Star Wars 2D Text-Based Rogue-like Game

Welcome to the Star Wars 2D Text-Based Rogue-like Game! This project extends an existing text-based game engine to create an immersive experience set in the iconic Star Wars universe. Utilizing Java code with a foundation in object-oriented design principles, this game allows players to step into the role of Luke Skywalker and offers an immersive journey through the galaxy far, far away.

This game is based on an engine (located in the packages with prefix edu.monash.fit2099) that has been used for several years for games based in many different pop-culture universes. Our team use the base engine to design some extensions to the functionality of a Star Wars roguelike game.

## Overview

In this game, players assume the role of Luke Skywalker, one of the central characters in the Star Wars saga. They will encounter and interact with other main actors such as Ben Kenobi, Droids, Sandcrawlers, and Tusken Raiders. Each actor in the game moves according to pre-programmed directions, exhibiting unique abilities and behaviors that contribute to the game's depth and complexity.


![game_screenshot](https://github.com/chailam/Star-War-2D-Game/assets/30400152/5bf6f4cb-b2c8-4cb3-8f89-5da1f0a581fd)
[Game Screenshot]

## Features

### Example Actors

- **Ben Kenobi:** A central character with special abilities related to the Force.
- **Droids:** Actors that can be owned by other characters and have distinct movement and health mechanics.
- **Sandcrawler:** A mobile base with the ability to capture Droids and transport them.
- **Tusken Raider:** An antagonistic character that adds an element of danger to the game world.

### Example Entities

- **Blaster:** A weapon that can be picked up and utilized by characters.
- **Door:** Provides access to various locations within the game world.
- **Fillable:** An entity that can be interacted with to alter its contents.
- **Lightsaber:** A powerful weapon that requires proficiency in the Force to wield effectively.
- **Grenade:** An explosive device with strategic implications in gameplay.

### Rules

The game adheres to several preset rules governing interactions between characters and entities. For example:

- Certain entities feature a Take affordance (e.g., lightsabres, blasters), enabling actors to pick up these items.   

- The Force ability is possessed by select individuals (such as Ben and Luke) within the game. 
- While anyone can pick up a lightsaber, only individuals with significant Force ability can effectively wield it as a weapon.
- Droids are unable to utilize the Force. Instead, they may have another actor as their owner. If a droid lacks an owner, it remains stationary. Furthermore, droids experience health loss when traversing the Badlands, resulting in immobility when their health is depleted. 
- If a sandcrawler encounters a droid within its location, the droid is captured and transported inside the sandcrawler.
- Sandcrawlers are equipped with doors accessible to any actor possessing Force ability within the same location. Upon entry into the sandcrawler, the actor transitions to its interior, which consists of a grid comprising at least four locations.


## How to Play

1. To embark on your adventure in the Star Wars universe, simply run the `Application.java` file. 


May the Force be with you as you journey through this text-based rogue-like adventure set in the beloved Star Wars universe!
