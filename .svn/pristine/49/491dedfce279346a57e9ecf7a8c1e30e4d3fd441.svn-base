#ifndef _CLASSFILE_H_
#define _CLASSFILE_H_

// This file is machine-readable. Do not edit.

#define ACC_PUBLIC		0x0001
#define ACC_PRIVATE		0x0002
#define ACC_PROTECTED 	0x0004
#define ACC_ABSTRACT 	0x0400  

extern char ClassFileHeader[1024];

typedef unsigned int U4;
typedef unsigned short U2;
typedef unsigned char U1;

// Yes, this is badly coded, and will be changed soon.
// i.e., every stucture defined here will be a class with a method to read the
// data from a file
#ifndef __GNUC__
	#pragma pack(1)
	#define __attribute__(packed)
#endif

typedef struct CONSTANT_Class_info {
 	U2 name_index;
} __attribute__((packed)) CONSTANT_Class_info ;

typedef struct CONSTANT_Fieldref_info {
 	U2 class_index;
 	U2 name_and_type_index;
} __attribute__((packed)) CONSTANT_Fieldref_info ;

typedef struct CONSTANT_Methodref_info {
 	U2 class_index;
 	U2 name_and_type_index;
} __attribute__((packed)) CONSTANT_Methodref_info ;

typedef struct CONSTANT_InterfaceMethodref_info {
 	U2 class_index;
 	U2 name_and_type_index;
} __attribute__((packed)) CONSTANT_InterfaceMethodref_info ;

typedef struct CONSTANT_String_info {
 	U2 string_index;
} __attribute__((packed)) CONSTANT_String_info ;

typedef struct CONSTANT_Integer_info {
 	U4 bytes;
} __attribute__((packed)) CONSTANT_Integer_info ;

typedef struct CONSTANT_Float_info {
 	U4 bytes;
} __attribute__((packed)) CONSTANT_Float_info ;

typedef struct CONSTANT_Long_info {
 	U4 high_bytes;
 	U4 low_bytes;
} __attribute__((packed)) CONSTANT_Long_info ;

typedef struct CONSTANT_Double_info {
 	U4 high_bytes;
 	U4 low_bytes;
} __attribute__((packed)) CONSTANT_Double_info ;

typedef struct CONSTANT_NameAndType_info {
 	U2 name_index;
 	U2 descriptor_index;
} __attribute__((packed)) CONSTANT_NameAndType_info ;

typedef struct CONSTANT_Utf8_info {
 	U2 length;
 	U1 *bytes;
} __attribute__((packed)) CONSTANT_Utf8_info ;

typedef union Cp_info_type{
	U1 info0;
	CONSTANT_Utf8_info info1;
	U1 info2;
	CONSTANT_Integer_info info3;
	CONSTANT_Float_info info4;
	CONSTANT_Long_info /*ONEMORE*/ info5;
	CONSTANT_Double_info /*ONEMORE*/ info6;
	CONSTANT_Class_info info7;
	CONSTANT_String_info info8;
	CONSTANT_Fieldref_info info9;
	CONSTANT_Methodref_info info10;
	CONSTANT_InterfaceMethodref_info  info11;
	CONSTANT_NameAndType_info info12;
} __attribute__((packed)) Cp_info_type ;

typedef struct Cp_info {
 	U1 tag;
 	Cp_info_type info;
} __attribute__((packed)) Cp_info;

typedef struct Attribute_info {
 	U2 attribute_name_index;
 	U4 attribute_length;
 	U1 *info;
} __attribute__((packed)) Attribute_info;

typedef struct Field_info {
 	U2 access_flags;
 	U2 name_index;
 	U2 descriptor_index;
 	U2 attributes_count;
 	Attribute_info *attributes;
} __attribute__((packed)) Field_info ;

typedef struct Method_info {
 	U2 access_flags;
 	U2 name_index;
 	U2 descriptor_index;
 	U2 attributes_count;
 	Attribute_info *attributes;
} __attribute__((packed)) Method_info ;

typedef struct ClassFile {
	U4 magic;
	U2 minor_version;
	U2 major_version;
	U2 constant_pool_count;
	Cp_info /*ONEOFF*/ *constant_pool;
	U2 access_flags;
	U2 this_class;
	U2 super_class;
	U2 interfaces_count;
	U2 *interfaces;
	U2 fields_count;
	Field_info *fields;
	U2 methods_count;
	Method_info *methods;
	U2 attributes_count;
	Attribute_info *attributes;
} __attribute__((packed)) ClassFile;

ClassFile *classfile_new(char *name);
void classfile_del(ClassFile *cf);

const char *class_get_utf8_constant(ClassFile *cf,int i);

#endif