#!/bin/bash

mkdir -p out

javac -d out $(find src -name "*.java")
java -cp out Game
