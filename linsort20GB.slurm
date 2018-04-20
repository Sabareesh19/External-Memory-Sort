#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort20GB.log
START_TIME=$(date +%s)
LC_ALL=C sort /input/data-20GB.in >> /tmp/lin20GBout.txt
END_TIME=$(date +%s)
Compute_time=$(($END_TIME - $START_TIME))
echo "$Compute_time seconds"

