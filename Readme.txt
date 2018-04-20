Running Environment: Neutron cluster(216.47.142.38)

Login to the compute node using the following command then execute the below 
instructions accordingly.

$ srun -n 1 --pty /bin/bash

Follow the below instructions to compile and run the scripts.

1. The entire sort algorithm for the Shared memory is available in source file Terasort.java
   $ javac Terasort.java

2. Once compiled run the executable, the binary file generated using
   $ java Terasort /input/data-2GB.in

3.Once compiled and run successfully the output has been generated in the /tmp folder.
  validate using the command $ valsort output.txt


To ease the above process makefile and slurm files are created just execute and follow the below instructions.
1. Execute the file naming Makefile.txt which contains the compile instructions
2. Run the slurm file to validate and verify the linsort and mysort
   For 2GB input data run $ sbatch linsort2GB.slurm
   For 20GB input data run $ sbatch linsort20GB.slurm
   For 2GB input data run $ sbatch mysort2GB.slurm
   For 20GB input data run $ sbatch mysort20GB.slurm

3. Refer the log files to verify the results of mysort and linsort datafiles.
 
