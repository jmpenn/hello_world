![trick_logo](https://raw.github.com/alexlin0/hello_world/master/trick-0.png)
==============
![hello_world](https://raw.github.com/alexlin0/hello_world/master/NWO.png)
==============
##Getting Started

###Compiling

```
$ javac -g HelloWorld.java
```

###Usage
$ java HelloWorld [ *recipient* ]

### Tables

Fruit   | Color
------- | -----
Apple   | Red
Banana  | Yellow
Cocanut | Brown
Grape   | Purple

### About Trick
Trick is developed on RHEL 6 and the free alternatives. (We use Scientific Linux)

Trick depends on quite a few packages.  To install these packages on yum based systems.

```shell
yum install bison flex gcc gcc-c++ java-1.7.0-openjdk libxml2-devel make openmotif \
openmotif-devel python python-devel perl swig zlib-devel
```

In addition, clang/llvm is also required and not included and will have to be compiled from source.

See full documentation at http://alexlin0.github.io/hello_world

###TMM_declare_var

Allocate a Trick variable, of the specified name, type and dimension.

```
void* TMM_declare_var( TRICK_TYPE type, const char*class_name, int n_stars, const char* var_name, int n_cdims, int *cdims);
```
- *type* - TRICK_TYPE.
- *class_name* - class or struct name if @b type is TRICK_STRUCTURED, otherwise @b class_name should be "".
- *n_stars* - number of asterisks in the variable declaration.
- *var_name* - (optional) name of the allocation. ="" for anonymous allocations.
- *n_cdims* - number of constrained/fixed dimensions. =0 for unarrayed variables.
- *cdims* - array of dimension sizes.
- **returns** - an address to the allocated memory or NULL on failure.

##License

We don't need no steekin' license!

