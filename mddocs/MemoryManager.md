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

### Memory Managers C and C++ Interfaces

Because the MemoryManager is written in C++, you can of course talk to it in C++.
The C++ header MemoryManager header file is: *MemoryManager.hh*

You can also talk to it in C. This is possible because the MemoryManager is unique within a simulation.
The C interface header file is: *memorymanager_c_intf.h*

Both header files are located in:
$TRICK_HOME/trick_source/sim_services/MemoryManager/include/

### Memory Allocation

To allocate Trick-managed memory several variations of 
void * Trick::MemoryManager::declare_var() is available.

The following is the variation most commonly used by Trick simulation developers.
The declaration information is specified entirely in a declaration string.

```
void * Trick::MemoryManager:: declare_var (const char *declaration);
```

The next variation of declare_var is a convenience function to allocate an anonymous,
one dimensional array:

```
void * Trick::MemoryManager:: declare_var (const char *type_spec, int n_elems);
```

The following variation is the the most flexible

```
void * Trick::MemoryManager:: declare_var (TRICK_TYPE type, std::string class_name, 
                    int n_stars, std::string var_name, int n_cdims, int *cdims);
```


### Example Memory Allocation using C++ Interface


```
#include "MemoryManager.hh"
extern trick::MemoryManager* trick_mm;
```
The following two calls do exactly the same thing.

```
double *D = (double*)trick_mm->declare_var("double[3]");
double *D = (double*)trick_mm->declare_var("double",3);
```

### Example Memory Allocation using C Interface

The following two calls do exactly the same thing as each other,
and as the two C++ examples above.

```
#include "memorymanager_c_intf.h"

double *D = (double*)TMM_declare_var_s("double[3]");
double *D = (double*)TMM_declare_var_1d("double",3);
```

###Declaration Strings
A declaration string provides a description of the contents of a chunk of memory. 

A MemoryManager declaration string consists of four parts from left to right:

1. One type specifier
2. Zero or more asterisks (pointers)
3. Zero or one (variable) names
4. Zero or more bracketed integers 

####Intrinsic Type Specifiers
"char", "unsigned char", "short", "unsigned short", "int", "unsigned int", "long",
"unsigned", "float", "double", "long long", "unsigned long long", "bool", "wchar_t"

####User Defined Type Specifiers

```
<user-defined-type> ::= NAME
                      | <user-defined-type> ":" ":" NAME

NAME ::=  [_a-zA-Z][_a-zA-Z0-9:]*
```

### Allocation Examples

Allocation of an anonymous singleton of type double:

```
double *D = (double*)TMM_declare_var_s("double");
```

Allocation of an anonymous array of 3 doubles:

```
double *D = (double*) TMM_declare_var_s("double[3]");
```

Allocation of an anonymous array of 3 pointers to double:

```
double **D = (double**) TMM_declare_var_s("double*[3]");
```

Allocation of a named singleton of type double:

```
double *D = (double*)TMM_declare_var_s("double mydbl"); 
```

Allocation of a named array of 3 Pointers to double:

```
double **D = (double**)TMM_declare_var_s("double* mydbl[3]");
```

Allocation of a named singleton of user-defined type "BAR":

```
BAR *D = (BAR*)TMM_declare_var_s("BAR mydbl");
```

Allocation of a named 2 dimensional array of user-defined type "BAR" in namespace "FOO":

```
FOO::BAR (*A)[3][4] = (FOO::BAR(*)[3][4])TMM_declare_var_s("FOO::BAR my_array[3][4]");
```

### String Duplication 

As a convenience, the following functions duplicate and catalog (as TRICK_LOCAL)
a given character string.

```
char * Trick::MemoryManager::mm_strdup (const char *s);
```

```
char * TMM_strdup (char *str);
```

### Resizing Arrays

The following functions resize arrays of one or more dimensions, preserving data
as possible. Dimensionality is also preserved, that is resizing a two dimensional
array will always result in a two dimensional array. Attempting to resize a
non-arrayed variable will result in an error.

Resize one-dimensional array by name:

```
void * Trick::MemoryManager::resize_array (const char *name, int num);
```

```
void * TMM_resize_array_1d_n (const char *name, int num);
```

Resize one-dimensional array by address:

```
void * Trick::MemoryManager::resize_array (void *address, int num);
```

```
void * TMM_resize_array_1d_a (void *address, int num);
```

Resize one-or-more-dimensional array by name:

```
void * Trick::MemoryManager::resize_array (const char *name, int n_cdims, int *cdims);
```

```
void * TMM_resize_array_n (const char *name, int n_cdims, int *cdims);
```

Resize one-or-more-dimensional array by name:

```
void * Trick::MemoryManager:: resize_array (void *address, int n_cdims, int *cdims);
```

```
void * TMM_resize_array_a (void *address, int n_cdims, int *cdims);
```

### Example of Resizing Arrays
```
#include "MemoryManager.hh"

extern trick::MemoryManager* trick_mm;

...

    // Create a 2x3 array.
    int (*original_array)[2][3] = (int(*)[2][3])trick_mm->declare_var("int[2][3]");

    // Assign some values to it's elements.
    (*original_array)[0][0] = 1;
    (*original_array)[0][1] = 2;
    (*original_array)[0][2] = 3;
    (*original_array)[1][0] = 4;
    (*original_array)[1][1] = 5;
    (*original_array)[1][2] = 6;

    // Resize the array to 5x7.
    int cdims[2] = {5,7};
    int (*resized_array)[5][7] =
                 (int(*)[5][7])trick_mm->resize_array(original_array, 2, cdims);

    // Verify that the elements common to both arrays have the same values.
    EXPECT_EQ( (*resized_array)[0][0], 1);
    EXPECT_EQ( (*resized_array)[0][1], 2);
    EXPECT_EQ( (*resized_array)[0][2], 3);
    EXPECT_EQ( (*resized_array)[1][0], 4);
    EXPECT_EQ( (*resized_array)[1][1], 5);
    EXPECT_EQ( (*resized_array)[1][2], 6);
...
```
###Deleting an Allocation
Allocations can be deleted by name or address.
```
int Trick::MemoryManager::delete_var (void *address);
```

```
int Trick::MemoryManager::delete_var (const char *var_name);
```

```
void TMM_delete_var_a(void* address);
```

```
void TMM_delete_var_n(const char* var_name);
```

###The Memory Catalog

###External Memory

Suppose that we already have a chunk of memory that the Memory Manager did not allocate, but we want the Memory Manager to know about (catalog) it so that we can checkpoint it, or manipulate it through the variable server.

To ask the Memory Manager to catalog a chunk of memory that has already been allocated you would call:

C++ Interface
```
void * Trick::MemoryManager:: declare_extern_var (void* address, const char *declaration);

void * Trick::MemoryManager: declare_extern_var(void *address, const char *element_definition, int n_elems);

void * Trick::MemoryManager: declare_extern_var(void *address, TRICK_TYPE type, std::string class_name, int n_stars, std::string var_name, int n_cdims, int *cdims);
```

C Interface
```
void* TMM_declare_ext_var_s( void* addr, const char* declaration);

void* TMM_declare_ext_var_1d( void* addr, const char* elem_decl, int e_elems);

void* TMM_declare_ext_var( void* addr, TRICK_TYPE type, const char*class_name, int n_stars, const char* var_name, int n_cdims, int *cdims);
```

###Local and External Memory

Note the difference between declare_var and declare_extern_var :

Allocate memory and catalog it as "TRICK_LOCAL" memory.

```
void * Trick::MemoryManager::declare_var (const char *declaration);
```

catalog a supplied allocation at address as "TRICK_EXTERN" memory.

```
void * Trick::MemoryManager::declare_extern_var (void* address, const char *declaration);
```

###Memory Checkpoint

Because the Memory Manager has complete descriptions of the allocations that its
managing, it can provide access to all of the current values contained in those
allocations. A snapshot of the values of the allocations is a checkpoint.

The Memory Manager calls upon a **checkpoint agent** to write a checkpoint to a
file or stream, The checkpoint agent transforms the data-type descriptions and
the values of memory manager managed allocations to a human readable text format
and writes it to a file or stream.

###Writing Checkpoints

Checkpoint every allocation that the MemoryManager knows about to a std::stream.
```
void Trick::MemoryManager::write_checkpoint (std::ostream &out_s);
```

Checkpoint the named allocation to a std::stream.

```
void Trick::MemoryManager::write_checkpoint (std::ostream &out_s, const char *var_name);
```

Checkpoint the listed named allocations to a std::stream.

```
void Trick::MemoryManager:: write_checkpoint (std::ostream &out_s, std::vector< const char * > &var_name_list);
```

Checkpoint every allocation that the MemoryManager knows about to a file.

```
void Trick::MemoryManager::write_checkpoint (const char *filename);
```

Checkpoint the named allocation to a file.

```
void Trick::MemoryManager::write_checkpoint (const char *filename, const char *var_name);
```

Checkpoint the listed named allocations to a file.

```
void Trick::MemoryManager:: write_checkpoint (const char *filename, std::vector< const char * > &var_name_list);
```

Checkpoint every allocation that the MemoryManager knows about to a file.

```
void  TMM_write_checkpoint( const char* filename) ;
```
![Figure1](images/MM_figure1.jpg)
