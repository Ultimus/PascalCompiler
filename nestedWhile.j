.bytecode 50.0
.class public nestedWhile
.super java/lang/Object
.method public <init>()V
	.limit stack 1
	.limit locals 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method
	.method public static main([Ljava/lang/String;)V
	.limit stack 11
	.limit locals 3
	ldc 8
	istore 1
	ldc 25
	istore 2
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	iload 1
	invokevirtual java/io/PrintStream/println(I)V
LabelWhile0:
	ldc 100
	iload 1
	isub
	iflt LabelTrue0
	bipush 0
	goto LabelCompareEnd0
LabelTrue0:
	bipush 1
LabelCompareEnd0:
	ifeq LabelWhileEnd0
LabelWhile1:
	ldc 25
	iload 2
	isub
	ifeq LabelTrue1
	bipush 0
	goto LabelCompareEnd1
LabelTrue1:
	bipush 1
LabelCompareEnd1:
	ifeq LabelWhileEnd1
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	iload 1
	invokevirtual java/io/PrintStream/println(Z)V
	iload 1
	ldc 1
	iadd
	istore 1
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	iload 1
	invokevirtual java/io/PrintStream/println(I)V
	goto LabelWhile1
LabelWhileEnd0:
	goto LabelWhile0
LabelWhileEnd1:
	return
.end method
