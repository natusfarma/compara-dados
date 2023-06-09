SELECT
	P.CD_PROD			AS CODIGO,
	P.DS_PROD			AS DESCRICAO,
	P.NR_NCM				AS NCM,	
	ISNULL((
			SELECT 
				TOP 1 B.CD_BARRA 
			FROM
				EST_PROD_CD_BARRA B 
			WHERE 
				B.CD_EMP = P.CD_EMP
				AND P.CD_PROD = B.CD_PROD
				AND B.FLAG_PRE_VENCIDOS <> 1
				AND B.NAO_USAR_PED_ELETR = 0
				AND B.EAN_CAIXA_FECHADA = 0
				AND B.NAO_EXPORTAR = 0
	),'0')					AS BARRAS,
	CASE
		WHEN  P.STS_PROD = 1
		THEN 'INATIVO'
		WHEN P.STS_PROD = 0
		THEN 'ATIVO'
		ELSE 'BLOQUEADO'
	END 						AS ATIVO
FROM
	EST_PROD P
WHERE
	P.CD_EMP = 1