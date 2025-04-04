@echo off

:: Definir o caminho do diretório atual
set CURRENT_DIR=%~dp0

:: Copiar os arquivos CSV para o container
docker cp "%CURRENT_DIR%..\downloads\fileTabula\1T2023.csv" meu-postgres:/tmp/1T2023.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\1T2024.csv" meu-postgres:/tmp/1T2024.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\2T2023.csv" meu-postgres:/tmp/2T2023.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\2T2024.csv" meu-postgres:/tmp/2T2024.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\3T2023.csv" meu-postgres:/tmp/3T2023.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\3T2024.csv" meu-postgres:/tmp/3T2024.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\4T2023.csv" meu-postgres:/tmp/4T2023.csv
docker cp "%CURRENT_DIR%..\downloads\fileTabula\4T2024.csv" meu-postgres:/tmp/4T2024.csv

:: Copiar o arquivo Relatorio_cadop.csv
docker cp "%CURRENT_DIR%..\downloads\Relatorio_cadop.csv" meu-postgres:/tmp/Relatorio_cadop.csv



:: Executar o comando \copy para importar os dados
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/1T2023.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/1T2024.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/2T2023.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/2T2024.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/3T2023.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/3T2024.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/4T2023.csv' DELIMITER ';' CSV HEADER;"
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy demonstracoes_contabeis FROM '/tmp/4T2024.csv' DELIMITER ';' CSV HEADER;"

:: Executar o comando \copy para importar os dados do Relatorio_cadop.csv
docker exec -it meu-postgres psql -U myuser -d mydb -c "\copy operadoras_ativas FROM '/tmp/Relatorio_cadop.csv' DELIMITER ';' CSV HEADER;"