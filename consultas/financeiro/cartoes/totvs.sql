/* ESTOU AGRUPANDO OS VALORES DE PARCELAMENTO PARA TER SOMENTE UMA LINHA DO CARTÃO */
SELECT 
	ZZ1_FILLIN											AS FILIAL,
	E1_NUM 												AS NUMERO,
	E1_CLIENTE 											AS CODCLIENTE,
	CONVERT(DATE, E1_EMISSAO, 103)	 		AS EMISSAO,
	SUM(E1_VALOR)										AS VALOR,
	E1_TIPO												AS TIPO,
	E1_NOMCLI											AS NOME_OPERADORA,
	'' 															AS OBS
FROM 
	SE1010 S
	INNER JOIN ZZ1010 F ON F.ZZ1_FILPRO = S.E1_FILORIG
WHERE 
	S.D_E_L_E_T_ = '' 
	AND F.D_E_L_E_T_ = ''
	AND S.E1_TIPO IN ('CC','CD','PIX') 
	
GROUP BY 
	ZZ1_FILLIN,
	E1_NUM 	,
	E1_CLIENTE,
	E1_EMISSAO,
	E1_TIPO	,
	E1_NOMCLI 