SELECT 
	B1_COD AS CODIGO,
	B1_DESC AS DESCRICAO,
	B1_POSIPI AS NCM,
	B1_CODBAR AS BARRAS,
	'ATIVO'  AS ATIVO
FROM 
	SB1010
WHERE 
	D_E_L_E_T_ = ''