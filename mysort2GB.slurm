#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=mysort2GB.log
javac Terasort.java
java Terasort /input/data-2GB.in >> /tmp/mysort2GBout.txt
valsort /tmp/mysort2GBout.txt

