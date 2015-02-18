## Trick Memory Manager

The Job of Trick Memory Management :

- Catalog data types.

- Catalog chunks of memory which are instances of those types.

- Provide services based on the cataloged information such as:

  - Creating new data type instances. (Memory allocation).

  -  Catalog pre-existing memory allocations.

  -  Writing values in memory to a file or stream. (checkpointing).

  -  Reading values from a file (or stream) back into memory (checkpoint-restore).

  -  Debugging help.

  -  C and C++ interfaces.

Makes other services possible, such as :

- Data recording

- Variable Server


