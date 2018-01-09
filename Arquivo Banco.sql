-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 19-Nov-2017 às 17:11
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `canal`
--

CREATE TABLE `canal` (
  `idCanal` int(11) NOT NULL,
  `nomeCanal` varchar(50) NOT NULL,
  `classEtaria` int(11) NOT NULL,
  `precoBase` float NOT NULL,
  `Categoria_idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `canal`
--

INSERT INTO `canal` (`idCanal`, `nomeCanal`, `classEtaria`, `precoBase`, `Categoria_idCategoria`) VALUES
(7, 'HBO', 18, 50, 2),
(8, 'TeleCine', 12, 50, 2),
(9, 'CNN', 0, 30, 3),
(10, 'GloboNews', 0, 30, 3),
(11, 'CBN', 0, 30, 3),
(12, 'Disney', 0, 35, 1),
(13, 'Nickelodeon', 0, 35, 1),
(14, 'SporTV', 0, 50, 4),
(15, 'Esporte Interativo', 0, 40, 4),
(16, 'ESPN', 0, 40, 4),
(17, 'Fox Sports', 0, 50, 4),
(18, 'Fox', 12, 50, 1),
(19, 'Cartoon Network', 0, 50, 1),
(20, 'Discovery Kids', 0, 30, 1),
(21, 'TNT', 18, 50, 2),
(22, 'Rede Viva', 12, 40, 2),
(23, 'Mega Pix', 12, 40, 2),
(24, 'Warner Bros.', 12, 50, 2),
(25, 'GNT', 0, 45, 3),
(26, 'Band News', 0, 25, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `nomeCategoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `nomeCategoria`) VALUES
(1, 'Infantil'),
(2, 'Filme'),
(3, 'Notícia'),
(4, 'Esporte');

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria-plano`
--

CREATE TABLE `categoria-plano` (
  `Categoria_idCategoria` int(11) NOT NULL,
  `Plano_idPlano` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `central`
--

CREATE TABLE `central` (
  `idCentral` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `endereco` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `central`
--

INSERT INTO `central` (`idCentral`, `login`, `senha`, `telefone`, `endereco`) VALUES
(1, 'javinha', '123456', '33712641', 'Rua do Albatroz');

-- --------------------------------------------------------

--
-- Estrutura da tabela `chamado`
--

CREATE TABLE `chamado` (
  `idChamado` int(11) NOT NULL,
  `data` date NOT NULL,
  `situacao` varchar(256) NOT NULL,
  `motivo` varchar(256) NOT NULL,
  `ultimaAtualizacao` date NOT NULL,
  `Contrato_idContrato` int(11) NOT NULL,
  `Contrato_Plano_idPlano` int(11) NOT NULL,
  `Contrato_Cliente_idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `chamado`
--

INSERT INTO `chamado` (`idChamado`, `data`, `situacao`, `motivo`, `ultimaAtualizacao`, `Contrato_idContrato`, `Contrato_Plano_idPlano`, `Contrato_Cliente_idCliente`) VALUES
(17, '2017-11-19', 'Em Aberto', 'Quebraram tudo', '2017-11-19', 11, 1, 5),
(18, '2017-11-19', 'Em Aberto', 'O animal doméstico destruiu o receptor', '2017-11-19', 11, 1, 5),
(19, '2017-11-19', 'Em Aberto', 'Controle quebrado', '2017-11-19', 11, 1, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nomeCliente` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `cpf` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nomeCliente`, `email`, `cpf`, `telefone`) VALUES
(5, 'Alex Freire Spinola', 'alexfreirespinola@gmail.com', '12345678911', '(71)33628505'),
(6, 'Thiago Armede', 'thiagoarmede@gmail.com', '12312312312', '(71)33670886'),
(7, 'Pedro Rosário', 'pedrorosario@gmail.com', '98745632100', '(71)33170051');

-- --------------------------------------------------------

--
-- Estrutura da tabela `contrato`
--

CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL,
  `Plano_idPlano` int(11) NOT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `quantReceptores` int(11) NOT NULL,
  `endCobranca` varchar(256) NOT NULL,
  `acesso` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `contrato`
--

INSERT INTO `contrato` (`idContrato`, `Plano_idPlano`, `Cliente_idCliente`, `quantReceptores`, `endCobranca`, `acesso`) VALUES
(11, 1, 5, 1, 'Rua do Albatroz', 1),
(12, 3, 6, 1, 'Rua dos Colibris', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contrato_has_categoria`
--

CREATE TABLE `contrato_has_categoria` (
  `Contrato_idContrato` int(11) NOT NULL,
  `Contrato_Plano_idPlano` int(11) NOT NULL,
  `Contrato_Cliente_idCliente` int(11) NOT NULL,
  `Categoria_idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `contrato_has_categoria`
--

INSERT INTO `contrato_has_categoria` (`Contrato_idContrato`, `Contrato_Plano_idPlano`, `Contrato_Cliente_idCliente`, `Categoria_idCategoria`) VALUES
(11, 1, 5, 1),
(12, 3, 6, 1),
(11, 1, 5, 2),
(12, 3, 6, 2),
(11, 1, 5, 3),
(11, 1, 5, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

CREATE TABLE `pagamento` (
  `idPagamento` int(11) NOT NULL,
  `data` date NOT NULL,
  `valor` float NOT NULL,
  `pago` tinyint(4) NOT NULL,
  `dataPagamento` date DEFAULT NULL,
  `Contrato_idContrato` int(11) NOT NULL,
  `Contrato_Plano_idPlano` int(11) NOT NULL,
  `Contrato_Cliente_idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`idPagamento`, `data`, `valor`, `pago`, `dataPagamento`, `Contrato_idContrato`, `Contrato_Plano_idPlano`, `Contrato_Cliente_idCliente`) VALUES
(20, '2017-11-19', 700, 0, NULL, 11, 1, 5),
(21, '2017-11-19', 700, 0, NULL, 11, 1, 5),
(22, '2017-11-19', 700, 1, '2017-11-19', 11, 1, 5),
(23, '2017-11-19', 384, 0, NULL, 12, 3, 6),
(24, '2017-11-19', 384, 0, NULL, 12, 3, 6),
(25, '2017-11-19', 384, 1, '2017-11-19', 12, 3, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `plano`
--

CREATE TABLE `plano` (
  `idPlano` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numeroCategorias` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `plano`
--

INSERT INTO `plano` (`idPlano`, `nome`, `numeroCategorias`) VALUES
(1, 'Ilimitado', 4),
(2, 'Top', 3),
(3, 'Regular', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `canal`
--
ALTER TABLE `canal`
  ADD PRIMARY KEY (`idCanal`,`Categoria_idCategoria`),
  ADD KEY `fk_Canal_Categoria_idx` (`Categoria_idCategoria`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indexes for table `categoria-plano`
--
ALTER TABLE `categoria-plano`
  ADD PRIMARY KEY (`Categoria_idCategoria`,`Plano_idPlano`),
  ADD KEY `fk_Categoria_has_Plano_Plano1_idx` (`Plano_idPlano`),
  ADD KEY `fk_Categoria_has_Plano_Categoria1_idx` (`Categoria_idCategoria`);

--
-- Indexes for table `central`
--
ALTER TABLE `central`
  ADD PRIMARY KEY (`idCentral`);

--
-- Indexes for table `chamado`
--
ALTER TABLE `chamado`
  ADD PRIMARY KEY (`idChamado`),
  ADD KEY `fk_Chamado_Contrato1_idx` (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `cpf_UNIQUE` (`cpf`);

--
-- Indexes for table `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`idContrato`,`Plano_idPlano`,`Cliente_idCliente`),
  ADD KEY `fk_Contrato_Plano1_idx` (`Plano_idPlano`),
  ADD KEY `fk_Contrato_Cliente1_idx` (`Cliente_idCliente`);

--
-- Indexes for table `contrato_has_categoria`
--
ALTER TABLE `contrato_has_categoria`
  ADD PRIMARY KEY (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`,`Categoria_idCategoria`),
  ADD KEY `fk_Contrato_has_Categoria_Categoria1_idx` (`Categoria_idCategoria`),
  ADD KEY `fk_Contrato_has_Categoria_Contrato1_idx` (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`);

--
-- Indexes for table `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`idPagamento`),
  ADD KEY `fk_Pagamento_Contrato1_idx` (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`);

--
-- Indexes for table `plano`
--
ALTER TABLE `plano`
  ADD PRIMARY KEY (`idPlano`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `canal`
--
ALTER TABLE `canal`
  MODIFY `idCanal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `central`
--
ALTER TABLE `central`
  MODIFY `idCentral` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `chamado`
--
ALTER TABLE `chamado`
  MODIFY `idChamado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `contrato`
--
ALTER TABLE `contrato`
  MODIFY `idContrato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `pagamento`
--
ALTER TABLE `pagamento`
  MODIFY `idPagamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `plano`
--
ALTER TABLE `plano`
  MODIFY `idPlano` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `canal`
--
ALTER TABLE `canal`
  ADD CONSTRAINT `fk_Canal_Categoria` FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `categoria-plano`
--
ALTER TABLE `categoria-plano`
  ADD CONSTRAINT `fk_Categoria_has_Plano_Categoria1` FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Categoria_has_Plano_Plano1` FOREIGN KEY (`Plano_idPlano`) REFERENCES `plano` (`idPlano`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `chamado`
--
ALTER TABLE `chamado`
  ADD CONSTRAINT `fk_Chamado_Contrato1` FOREIGN KEY (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`) REFERENCES `contrato` (`idContrato`, `Plano_idPlano`, `Cliente_idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_Contrato_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_Plano1` FOREIGN KEY (`Plano_idPlano`) REFERENCES `plano` (`idPlano`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `contrato_has_categoria`
--
ALTER TABLE `contrato_has_categoria`
  ADD CONSTRAINT `fk_Contrato_has_Categoria_Categoria1` FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_has_Categoria_Contrato1` FOREIGN KEY (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`) REFERENCES `contrato` (`idContrato`, `Plano_idPlano`, `Cliente_idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD CONSTRAINT `fk_Pagamento_Contrato1` FOREIGN KEY (`Contrato_idContrato`,`Contrato_Plano_idPlano`,`Contrato_Cliente_idCliente`) REFERENCES `contrato` (`idContrato`, `Plano_idPlano`, `Cliente_idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
