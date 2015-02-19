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

Declaration Strings
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



