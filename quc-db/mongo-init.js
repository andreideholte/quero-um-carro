print('Start Script MONGO INIT ###########################################################');

db = db.getSiblingDB('quc_db');

db.createCollection('veiculo');
db.createCollection('usuario');
db.createCollection('reserva');

db.veiculo.insertMany([
 {
    marca: 'Mitsubishi Motors',
    modelo: 'Pajero FUll Legend Edition',
    ano: '2021',
    cor: 'Prata',
    quilometragem: '20445',
    imagem: 'https://cdn.motor1.com/images/mgl/9qGev/s1/mitsubishi-pajero-full-legend-edition-2021.jpg'
  },
  {
    marca: 'Audi',
    modelo: 'A3',
    ano: '2021',
    cor: 'Preto',
    quilometragem: '12703',
    imagem: 'https://www.audi.com.br/content/dam/nemo/br/models/A3/2021/sedan/1920x1920-audi-a3-sedan-my2021-1021_retoque.jpg'
  },
  {
    marca: 'Land Rover',
    modelo: 'Range Rover Evoque',
    ano: '2021',
    cor: 'Preto',
    quilometragem: '6841',
    imagem: 'https://www.landrover.com.br/sdlmedia/637329105877167115CF.jpg'
  }  
]);

print('Stop Script MONGO INIT ############################################################');
