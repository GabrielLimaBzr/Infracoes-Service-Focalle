Desafio Java
Este desafio consiste no desenvolvimento de uma API e um Serviço.
# ----------------------------------------------------------------------------------------------------#
API – Central de Infrações
A API deve ser desenvolvida em Java 11 + Spring Boot + Maven + PostgreSQL e ser responsável por
gerenciar uma única tabela no banco de dados, chamada “records”. Na tabela “records”, a API deve
permitir que os seguintes dados de veículos infratores sejam salvos:
● Data/Hora (Ex: 2022-06-09 09:52:12)
● Velocidade (Ex: 80)
● Placa (Ex: ABC1234)
● Classe do Veículo (Ex: Carro, Moto, Caminhão, etc.)
# ----------------------------------------------------------------------------------------------------#
Serviço – Simulador de Infrações
O serviço deve ser desenvolvido em Java 11 e simular um Radar que enviará infrações para a API –
Central de Infrações. O serviço deve ser um “loop”, que a cada 3 segundos, envia uma Infração
para a API.
Os dados de uma Infração são:
● Data/Hora atual
● Uma velocidade aleatória entre 80 e 100 km/h
● Uma placa de veículo aleatória
● Uma classe de veículo aleatória
# ----------------------------------------------------------------------------------------------------#
Bônus
Se você possui conhecimentos em front-end e deseja criar uma interface gráfica para visualizar as
Infrações simuladas, será um diferencial!
