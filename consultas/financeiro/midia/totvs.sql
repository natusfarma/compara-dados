SELECT 
	ZZ1_FILLIN										AS FILIAL,
	E1_NUM 											AS NUMERO,
	E1_CLIENTE 										AS CODCLIENTE,
	CONVERT(DATE, E1_EMISSAO, 103)	 				AS EMISSAO,
	CONVERT(DATE, E1_VENCTO, 103)	 				AS VENCIMENTO,
	E1_VALOR										AS VALOR,
	E1_SALDO										AS SALDO,
	E1_NOMCLI										AS NOME,
	CASE 
		WHEN NULLIF(E1_BAIXA,'') IS NULL 
		THEN 'EM ABERTO'
		ELSE 'QUITADA'
	END												AS STS_DP
	--,*
FROM 
	SE1010 S
	INNER JOIN ZZ1010 F 
		ON F.ZZ1_FILPRO = S.E1_FILORIG
		AND F.D_E_L_E_T_ = ''
WHERE 
	S.D_E_L_E_T_ = ''
	AND E1_TIPO = 'MD' 