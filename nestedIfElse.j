.bytecode 50.0
.class public nestedIfElse
.super java/lang/Object
.method public <init>()V
	.limit stack 1
	.limit locals 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method
	.method public static main([Ljava/lang/String;)V
	.limit stack 7
	.limit locals 4
	ldc 5
	istore 1
	ldc 88
	istore 3
	ldc 88
	ldc 5
	isub
	iflt LabelTrue0
	bipush 0
	goto LabelCompareEnd0
LabelTrue0:
	bipush 1
LabelCompareEnd0:
	istore 2
	iload 2
	ifeq LabelOpenIf0
	iload 1
	ldc 3
	iadd
	istore 1
LabelOpenIf0:
	iload 3
	iload 1
	isub
	ifge LabelTrue1
	bipush 0
	goto LabelCompareEnd1
LabelTrue1:
	bipush 1
LabelCompareEnd1:
	ifeq LabelNot2
	bipush 0
	goto LabelNotEnd2
LabelNot2:
bipush 1
LabelNotEnd2:
	iload 2
	iand
	ifeq LabelClosedIf1
	iload 3
	ldc 44
	isub
	istore 3
LabelClosedIf1:
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	iload 2
	invokevirtual java/io/PrintStream/println(I)V
	return
.end method
