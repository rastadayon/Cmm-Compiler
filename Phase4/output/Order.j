.class public Order
.super java/lang/Object
		 
.field quantity Ljava/lang/Integer;
.field product LProductCatalog;
.method public <init>()V
.limit stack 128
.limit locals 128
		aload 0
		invokespecial java/lang/Object/<init>()V
		aload 0
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield Order/quantity Ljava/lang/Integer;
		aload 0
		aconst_null
		putfield Order/product LProductCatalog;
		return
.end method
		 
