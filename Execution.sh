#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort2GB.log
START_TIME=$(date +%s)
LC_ALL=C sort /input/data-2GB.in >> /tmp/lin2GBout.txt
END_TIME=$(date +%s)
Compute_time=$(($END_TIME - $START_TIME))
echo "$Compute_time seconds"



