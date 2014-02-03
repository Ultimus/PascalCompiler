.bytecode 50.0
.class public ifElseExample
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
	ldc 3
	istore 1
	ldc 23
	istore 3
	bipush 1
	istore 2
	ldc 5
	iload 1
	isub
	iflt LabelTrue0
	bipush 0
	goto LabelCompareEnd0
LabelTrue0:
	bipush 1
LabelCompareEnd0:
	istore 2
LabelWhile0:
	bipush 1
	ifeq LabelWhileEnd0
	ldc 2
	iload 3
	isub
	ifeq LabelTrue1
	bipush 0
	goto LabelCompareEnd1
LabelTrue1:
	bipush 1
LabelCompareEnd1:
	ifeq LabelIfControl1
	ldc 8
	istore 1
	goto LabelIfControl1
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	iload 1
	invokevirtual java/io/PrintStream/println(I)V
	goto LabelWhile0
LabelWhileEnd0:
	return
.end method
