-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  mer. 13 fév. 2019 à 22:53
-- Version du serveur :  5.6.35
-- Version de PHP :  7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `proassure`
--

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE `contrat` (
  `id` int(11) NOT NULL,
  `user_id` int(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `datedeb` date NOT NULL,
  `datefin` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contrat`
--

INSERT INTO `contrat` (`id`, `user_id`, `description`, `datedeb`, `datefin`, `created_at`, `updated_at`) VALUES
(1, 6, 'ggg', '2019-02-09', '2019-02-10', '2019-02-13 10:35:13', '2019-02-13 10:35:13'),
(2, 6, 'ggg', '2019-02-09', '2019-02-10', '2019-02-13 10:35:25', '2019-02-13 10:35:25');

-- --------------------------------------------------------

--
-- Structure de la table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Structure de la table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` int(1) NOT NULL DEFAULT '0',
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `type`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'khemim oussama', 'oussamakhemim@gmail.com', 0, NULL, '$2y$10$p7i5TLRuVPfcU1b9jaK0LONtne3BZ8.R5PKVOE8Y2saQfINqMLNwy', NULL, '2019-02-12 17:48:26', '2019-02-12 17:48:26'),
(4, 'test', 'test@test.com', 0, NULL, '$2y$10$iy9BSNlP/OiKO2kyaIkjc.9n8y9B9zXHwUASIuAAADdzuWBBvnNVC', NULL, '2019-02-12 19:04:02', '2019-02-12 19:04:02'),
(6, 'ghhchhjhn', 'oussamakhemim@gmail.c', 4, NULL, '$2y$10$Uko9c.xzrjljj2v.whN0neuLowLDXK7dZBCYaQxU5NzO9cWTcj3oO', NULL, '2019-02-12 19:16:55', '2019-02-12 19:16:55'),
(7, 'Med Mokrani', 'mokrani.med@hotmail.com', 0, NULL, '$2y$10$YyKbvJVyAW3rNsiz3CDRGOa3CqHuYkYoiwTu3ZD2o9BKlMeyTpy.u', NULL, '2019-02-12 23:05:18', '2019-02-12 23:05:18'),
(8, 'Med Mokrani', 'mokrani.med@hotmail.com', 2, NULL, '$2y$10$mwSNwYxOWln00cL55JCs/OAK.AC9iD05Y7nkilzjZO29at16qHAFO', NULL, '2019-02-13 11:22:28', '2019-02-13 11:22:28'),
(9, 'Riadh Bhar', 'mokrani.med@hotmail.fr', 1, NULL, '$2y$10$0fNoTrM5yhUT/NnqTdwruuwsrVaWy9juWswLqaxzk2b8xCq.8ivK.', NULL, '2019-02-13 13:22:52', '2019-02-13 13:22:52');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`(191));

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contrat`
--
ALTER TABLE `contrat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;