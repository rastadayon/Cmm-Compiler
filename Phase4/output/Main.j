.class public Main
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 128
.limit locals 128
		new Main
		invokespecial Main/<init>()V
		return
.end method
.method public <init>()V
.limit stack 128
.limit locals 128
		aload 0
		invokespecial java/lang/Object/<init>()V
		aconst_null
		astore 1
		aconst_null
		astore 2
		
		new Fptr
		dup
		aload 0
		ldc "foo"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		dup
		astore 1
		pop
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 4
		aload 1
		aload 4
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		
		new Fptr
		dup
		aload 0
		ldc "bar"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		dup
		astore 2
		pop
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 5
		aload 5
		ldc 5
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 2
		aload 5
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		return
.end method
		 
.method public foo()V
.limit stack 128
.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc 10001
		invokevirtual java/io/PrintStream/println(I)V
		return
.end method
		 
.method public bar(Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 0
		if_icmpge Label_2
		ldc 1
		goto Label_3
		Label_2:
		ldc 0
		Label_3:
		ifeq Label_0
		return
		goto Label_1
	Label_0:
	Label_1:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 2
		imul
		invokevirtual java/io/PrintStream/println(I)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 4
		aload 4
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 1
		isub
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		
		new Fptr
		dup
		aload 0
		ldc "bar"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		aload 4
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
.end method
		 
