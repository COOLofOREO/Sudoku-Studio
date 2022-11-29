#!/usr/bin/bash
# compile all java source file

# 

if [ "$1" = "build" ]
then
    if [ -d "./sudoku" ]
    then
        rm -rf ./sudoku
    fi
    javac -d . ./src/sudoku/*/*.java

elif [ "$1" = "--mode" ]
then
    if [ "$2" = "dfs" ]
    then
        java sudoku.test.Main dfs
    elif [ "$2" = "bfs" ]
    then
        java sudoku.test.Main bfs
    fi
else
    java sudoku.test.Main
fi