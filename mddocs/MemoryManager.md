## Trick Memory Manager

The Trick MemoryManager is a C++ Class that provides memory tracking, allocation,
checkpointing and various other memory-resource administration services. It is
a "keeper of knowledge" about variables (chunks of memory). It manages variables
that a user has asked it to allocate as well as user-allocated variables that
the user tells it about.

Variables whose memory is allocated by the memory manager are called **TRICK_LOCAL** variables. Those allocated by the user are **TRICK_EXTERN** variables. Variables can also optionally be named.

TRICK_LOCAL variables are declared using **Trick::MemoryManager::declare_var**.  This function allocates memory according to a user-provided declaration. Information about the allocation, and its declared type are retained.

TRICK_EXTERN variables are declared using: **Trick::MemoryManager::declare_extern_var** This function stores information about a chunk of memory that the user has allocated and has declared to be of a given type.

By tracking allocation and type information, the Memory Manager can, on request, write  human-readable representations of memory resources to a file.  To write the value(s) of a single variable to a file, use **Trick::MemoryManager::write_var**.  To write a checkpoint, consisting of declarations and values of all variables known to the memory manager, use **Trick::MemoryManager::write_checkpoint**.  These memory resources can later be restored from this checkpoint file using **Trick::MemoryManager::read_checkpoint or **Trick::MemoryManager::init_from_checkpoint**.

The Memory Manager provides memory-resource administration services.
To provide these services, it tracks information about chunks of memory.

These chunks of memory can come from one of two places:
@li You can ask the Memory Manager to allocate memory according a
declaration [using declare_var()].
@li You can provide an address to memory that you've somehow acquired,
together with a declaration of what's at that address
[using declare_extern_var()].

By tracking the address and declaration information, the Memory Manager
it is able, on request, to write a human-readable representation of those memory
resources to a file [using write_checkpoint()]. The memory resources can be later
restored from this checkpoint file using read_checkpoint() or init_from_checkpoint().

It can also return addresses of named subcomponents of memory resources.




See Also:
- Trick::MemoryManager
- @subpage defsMemoryManager "Memory Manager Definitions"
- @subpage ADEF_Syntax "Allocation Declaration Syntax"
- @subpage examples_declare_var
- @subpage convert_07_to_10
- @subpage placement_new_init
