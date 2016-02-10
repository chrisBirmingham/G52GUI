# Java Music Player #

## About ##
A simple music player written in Java using JavaFX for its GUI. Created for my
G52GUI module at the University of Nottingham

## What it can do ##
* It can play music
* It will move into the next song when the current one finishes
* It can list songs by their album
* A user can move forward and backwards in an album
* A user can modify a songs metadata
* A user can add music

## Requirements ##
* JavaFX
* [JAudioTagger library](http://www.jthink.net/jaudiotagger/)

## Bugs ##
On editing a file the library may delete the file. To counteract this I copy the
file to replace a deleted file but on success the copy is not deleted and will
be shown on the main screen
