##Trick Memory Manager C Interface

###TMM_declare_var

Allocate a variable, of the specified name, type, and dimension. Then register that variable 

```
void* TMM_declare_var( TRICK_TYPE type, const char*class_name, int n_stars, const char* var_name, int n_cdims, int *cdims);
```

- *type* - [TRICK_TYPE](TypeSpecEnums.md).
- *class_name* - class or struct name if *type* is TRICK_STRUCTURED, otherwise *class_name* should be "".
- *n_stars* - number of asterisks in the variable declaration.
- *var_name* - name of the allocation. ="" for anonymous allocations.
- *n_cdims* - number of constrained/fixed dimensions. =0 for unarrayed variables.
- *cdims* - array of dimension sizes.
- **return** - an address to the allocated variable or NULL on failure.


###TMM_declare_var_1d

Allocate an anonymous, one dimensional array. The elements of the array are specified by the enhanced-type-specifier. The length of the array is specified by *n_elems*.

```
void* TMM_declare_var_1d( const char* elem_decl, int e_elems) ;
```
- *enh_type_spec* - type specifier followed by zero or more asterisks.
- *n_elems* - The number of items of the given type to allocate.
- **return** - an address to the allocated variable or NULL on failure.


###TMM_declare_var_s
Allocate Trick variable as specified by the given declaration string.

```
void* TMM_declare_var_s( const char* declaration ) ;
```
- *declaration* - a [declaration string] (Declaration.md).
- **return** - an address to the allocated memory or NULL on failure.


###TMM_declare_ext_var_s
```
void* TMM_declare_ext_var( void* addr, TRICK_TYPE type, const char*class_name, int n_stars, const char* var_name, int n_cdims, int *cdims);
```

###TMM_declare_ext_var_1d
```
void* TMM_declare_ext_var_1d( void* addr, const char* elem_decl, int e_elems) ;
```

###TMM_declare_ext_var_s
```
void* TMM_declare_ext_var_s( void* addr, const char* declaration);
```

###TMM_declare_operatornew_var
```
void* TMM_declare_operatornew_var( const char * class_name, unsigned int alloc_size, unsigned int element_size );
```

###TMM_resize_array_a
```
void* TMM_resize_array_a(void *address, int n_cdims, int *cdims);
```

###TMM_resize_array_n
```
void* TMM_resize_array_n(const char *name, int n_cdims, int *cdims);
```

###TMM_resize_array_1d_a
```
void* TMM_resize_array_1d_a(void* address, int num);
```

###TMM_resize_array_1d_n
```
void* TMM_resize_array_1d_n(const char *name, int num);
```

###TMM_strdup
```
char* TMM_strdup(char *str) ;
```

###TMM_var_exists
```
int   TMM_var_exists( const char* var_name);
```

###TMM_is_alloced
```
int   TMM_is_alloced(char *addr) ;
```

###TMM_set_debug_level
```
void  TMM_set_debug_level(int level);
```

###TMM_reduced_checkpoint
```
void  TMM_reduced_checkpoint(int flag);
```

###TMM_hexfloat_checkpoint
```
void  TMM_hexfloat_checkpoint(int flag);
```

###TMM_clear_var_a
```
void  TMM_clear_var_a( void* address);
```

###TMM_clear_var_n
```
void  TMM_clear_var_n( const char* var_name );
```

###TMM_delete_var_a
```
void  TMM_delete_var_a( void* address);
```

###TMM_delete_var_n
```
void  TMM_delete_var_n( const char* var_name );
```

###TMM_delete_extern_var_a
```
void  TMM_delete_extern_var_a( void* address);
```

###TMM_delete_extern_var_n
```
void  TMM_delete_extern_var_n( const char* var_name );
```

###TMM_write_checkpoint
```
void  TMM_write_checkpoint( const char* filename) ;
```

###TMM_read_checkpoint
```
int   TMM_read_checkpoint( const char* filename);
```

###TMM_read_checkpoint_from_string
```
int   TMM_read_checkpoint_from_string( const char* str);
```

###TMM_init_from_checkpoint
```
int   TMM_init_from_checkpoint( const char* filename);
```

