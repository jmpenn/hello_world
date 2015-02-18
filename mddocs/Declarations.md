### Declaration Syntax

An allocation declaration string is used to declare type information for a chunk of memory.

They are taken as parameters by the Memory Managers declare_var and declare_ext_var member functions as well as "C" interface calls TMM_declare_var_s and TMM_declare_ext_var_s.

An allocation declaration is an intrinsic type or a user-defined-type followed by a declarator. A declarator is zero or more asterisks (pointers), followed by an optional variable name, followed by zero or more bracketed integers (a dimension list).

An intrinsic type is one of the built-in types, like *char*, *int*, *double*, etc.

A user defined type is a typedef-struct name or a class name, including if necessary, scope resolution operators ( i.e., "::").

##Examples Variable Declaration strings:

- double
- double[3]
- double*[3]
- char[3][4]
- int narf
- int *baz[3]
- VEHICLE truck
- FOO::BAR zortz[2][5]
- long*
- double entendre
- char* broiled
- double[3]
- float* [4]
- int foo[5]
- Ship* armada[4]


##Syntax of Variable Declaration

```
<alloc-declaration> ::= <intrinsic-type> <declarator>
                      | <user-defined-type> <declarator>

<declarator> ::= <pointers> <opt-name> <dim-list>

<opt-name> ::= nil
             | NAME

<pointers> ::= nil
             | pointers '*'

<user-defined-type> ::= NAME
                      | <user-defined-type> ":" ":" NAME

<dim-list> ::= nil
                 | <dim-list> "[" INTEGER "]"

intrinsic_type ::= "char"
                 | "short"
                 | "int"
                 | "long"
                 | "float"
                 | "double"
                 | "long long"
                 | "wchar_t"
                 | "unsigned char"
                 | "unsigned short"
                 | "unsigned int"
                 | "unsigned long"
                 | "unsigned long long"

NAME ::=  [_a-zA-Z][_a-zA-Z0-9:]*
```
