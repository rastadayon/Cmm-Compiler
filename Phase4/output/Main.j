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
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 10
		new List
		dup
		aload 10
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		astore 3
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 10
		new List
		dup
		aload 10
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		astore 4
		new Order
		dup
		invokespecial Order/<init>()V
		astore 5
		new ProductCatalog
		dup
		invokespecial ProductCatalog/<init>()V
		astore 6
		new ProductCatalog
		dup
		invokespecial ProductCatalog/<init>()V
		astore 7
		new ProductCatalog
		dup
		invokespecial ProductCatalog/<init>()V
		astore 8
		new ProductCatalog
		dup
		invokespecial ProductCatalog/<init>()V
		astore 9
		aload 6
		ldc 0
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/id Ljava/lang/Integer;
		pop
		aload 7
		ldc 1
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/id Ljava/lang/Integer;
		pop
		aload 8
		ldc 2
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/id Ljava/lang/Integer;
		pop
		aload 9
		ldc 3
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/id Ljava/lang/Integer;
		pop
		aload 3
		aload 6
		invokevirtual List/addElement(Ljava/lang/Object;)V
		aload 3
		aload 7
		invokevirtual List/addElement(Ljava/lang/Object;)V
		aload 3
		aload 8
		invokevirtual List/addElement(Ljava/lang/Object;)V
		aload 3
		aload 9
		invokevirtual List/addElement(Ljava/lang/Object;)V
		aload 3
		ldc 0
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 5000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 1
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 4000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 2
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 2000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 3
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 8000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		ldc 0
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		pop
	Label_0:
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 4
		if_icmpge Label_2
		ldc 1
		goto Label_3
		Label_2:
		ldc 0
		Label_3:
		ifeq Label_1
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 10
		aload 10
		aload 3
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 10
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 1
		iadd
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		
		new Fptr
		dup
		aload 0
		ldc "createOrder"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		aload 10
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		
		checkcast Order
		dup
		astore 5
		pop
		aload 4
		aload 5
		invokevirtual List/addElement(Ljava/lang/Object;)V
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		ldc 1
		iadd
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		pop
		goto Label_0
	Label_1:
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 11
		aload 11
		aload 4
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		
		new Fptr
		dup
		aload 0
		ldc "getSum"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		aload 11
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		pop
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		return
.end method
		 
.method public createOrder(LProductCatalog;Ljava/lang/Integer;)LOrder;
.limit stack 128
.limit locals 128
		new Order
		dup
		invokespecial Order/<init>()V
		astore 3
		aload 3
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield Order/quantity Ljava/lang/Integer;
		pop
		aload 3
		aload 1
		dup_x1
		putfield Order/product LProductCatalog;
		pop
		aload 3
		areturn
.end method
		 
.method public getSum(LList;)Ljava/lang/Integer;
.limit stack 128
.limit locals 128
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 3
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 4
	Label_4:
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		aload 1
		invokevirtual List/getSize()I
		
		if_icmpge Label_6
		ldc 1
		goto Label_7
		Label_6:
		ldc 0
		Label_7:
		ifeq Label_5
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast Order
		getfield Order/product LProductCatalog;
		getfield ProductCatalog/price Ljava/lang/Integer;
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast Order
		getfield Order/product LProductCatalog;
		getfield ProductCatalog/price Ljava/lang/Integer;
		invokevirtual java/lang/Integer/intValue()I
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast Order
		getfield Order/quantity Ljava/lang/Integer;
		invokevirtual java/lang/Integer/intValue()I
		imul
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast Order
		getfield Order/quantity Ljava/lang/Integer;
		invokevirtual java/lang/Integer/intValue()I
		ldc 100
		imul
		iadd
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast Order
		getfield Order/product LProductCatalog;
		getfield ProductCatalog/price Ljava/lang/Integer;
		invokevirtual java/lang/Integer/intValue()I
		ldc 100
		idiv
		isub
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 4
		pop
		aload 3
		invokevirtual java/lang/Integer/intValue()I
		aload 4
		invokevirtual java/lang/Integer/intValue()I
		iadd
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 3
		pop
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 1
		iadd
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		pop
		goto Label_4
	Label_5:
		aload 3
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		areturn
.end method
		 
