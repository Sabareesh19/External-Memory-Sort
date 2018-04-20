#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=mysort20GB.log
javac Terasort.java
java Terasort /input/data-20GB.in >> /tmp/mysort20GBout.txt
valsort /tmp/mysort20GBout.txt


