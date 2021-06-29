print('Start Script MONGO INIT ###########################################################');

db = db.getSiblingDB('quc_db');

db.createCollection('veiculo');
db.createCollection('usuario');
db.createCollection('reserva');

db.veiculo.insertMany([
 {
    marca: 'Mitsubishi Motors',
    modelo: 'Pajero Full Legend Edition',
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
    imagem: 'https://cdn.autopapo.com.br/box/uploads/2021/05/10102238/audi-a3-sedan-2022-preto-scaled.jpg'
  },
  {
    marca: 'Land Rover',
    modelo: 'Range Rover Evoque',
    ano: '2021',
    cor: 'Preto',
    quilometragem: '6841',
    imagem: 'https://quatrorodas.abril.com.br/wp-content/uploads/2016/11/5658bd2ecc505d1bd7878b01range-rover-sicilian-yellow-1.jpeg'
  }  
]);

print('Stop Script MONGO INIT ############################################################');
