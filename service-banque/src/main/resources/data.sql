INSERT INTO Utilisateur (numero_passeport, date_de_naissance, nom, numero_telephone, pays, prenom, secret)
VALUES ('76TE87654', '1997-01-02', 'Mercier', '+33649235682', 'France', 'Clément',
        '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

INSERT INTO Compte (iban, solde, utilisateur_numero_passeport)
VALUES ('FR2030003000701632956183R61', 5000000, null)
