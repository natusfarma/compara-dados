SELECT 
	EST_PROD_INVENTARIO.CD_FILIAL 											AS FILIAL,
    COUNT(EST_PROD_INVENTARIO.CD_PROD) 							AS QTDE_ITENS,
    SUM(EST_PROD_INVENTARIO.QT_EST * 
		ROUND(EST_PROD_INVENTARIO.VLR_PROD, 2)) 					AS CUSTO,
	EST_PROD_INVENTARIO.DT_REF 												AS DT_REF
FROM 
	EST_PROD_INVENTARIO
WHERE 
	EST_PROD_INVENTARIO.CD_EMP =1
	AND EST_PROD_INVENTARIO.DT_REF = '20230430'
GROUP BY 
	EST_PROD_INVENTARIO.CD_FILIAL,
	EST_PROD_INVENTARIO.DT_REF