
/****** Object:  Table [dbo].[Access Control]    Script Date: 10/03/2023 08:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Access Control](
	[UserName] [nvarchar](50) NOT NULL,
	[Matricule] [nvarchar](10) NOT NULL,
	[Description] [nvarchar](60) NULL,
	[PassWord] [nvarchar](40) NULL,
	[Grp] [nvarchar](2000) NULL,
	[Old_Grp] [nvarchar](200) NOT NULL,
	[Code_Medecin_Infirmier] [nvarchar](10) NULL,
	[Actif] [nvarchar](1) NOT NULL,
	[Ch_Stat] [nvarchar](1) NOT NULL,
	[Dernier_Date_Cnx] [datetime] NULL,
	[Date_Mod_Pwd] [datetime] NULL,
	[TraceNotif] [nvarchar](max) NULL,
	[Valid_Cpt_Rend] [bit] NULL,
	[Code_Carte] [nvarchar](10) NULL,
	[Code_Carte_Minus] [nvarchar](10) NULL,
	[Cledallas] [nvarchar](20) NOT NULL,
	[styleClaire] [bit] NOT NULL,
	[Secretaire] [bit] NULL,
	[CompteExpire] [bit] NOT NULL,
	[dateExpirationCompte] [datetime] NULL,
	[expire] [bit] NOT NULL,
	[nbre_jours_expiration] [int] NOT NULL,
	[cptShowAllPatient] [bit] NOT NULL,
	[cptconsultActivityAll] [bit] NOT NULL,
	[UserModif] [nvarchar](20) NULL,
	[Admin_Ev_Indesirable] [bit] NULL,
	[Valid_PH_Nuit] [bit] NOT NULL,
	[nature_user_DS] [nvarchar](50) NOT NULL,
	[codePin] [nvarchar](6) NOT NULL,
	[Autorise_av] [nvarchar](1) NOT NULL,
	[Valid_PH_Stupefiant] [bit] NOT NULL,
	[NatBio] [nvarchar](50) NOT NULL,
	[Financier] [bit] NOT NULL,
	[Type_user] [nvarchar](10) NOT NULL,
	[caissier] [bit] NOT NULL,
	[Autorise_securite] [bit] NOT NULL,
	[MatriculeResp] [nvarchar](15) NOT NULL,
	[mat] [nvarchar](10) NOT NULL,
	[afficheStatCpt] [bit] NOT NULL,
	[Access_Planning_actuelle] [bit] NOT NULL,
	[Access_Planning_anterieur] [bit] NOT NULL,
	[Access_DAF] [bit] NOT NULL,
	[Arrangement] [bit] NOT NULL,
	[Rapprochement_Mois_diff] [bit] NOT NULL,
	[fonction] [nchar](50) NULL,
	[date_creation_compte,CodeEmployer
date_creation_compte] [date] NULL,
	[CodeEmployer] [nvarchar](10) NOT NULL,
	[date_creation_compte] [datetime] NULL,
	[CptDictaphoneAuto] [bit] NOT NULL,
	[cpt_affichehistorique] [bit] NOT NULL,
	[cpt_afficheboutons] [bit] NOT NULL,
	[cpt_afficheAdicter] [bit] NOT NULL,
	[cpt_afficheAValider] [bit] NOT NULL,
	[cpt_afficheMedSeclionner] [bit] NOT NULL,
	[Email] [nvarchar](200) NULL,
	[PasswordEmail] [nvarchar](30) NULL,
	[Referent_EvntInd] [bit] NULL,
	[type_signature] [nvarchar](200) NOT NULL,
	[signature_text] [nvarchar](200) NOT NULL,
	[signature] [varbinary](max) NULL,
	[Valid_PH_Stupefiant_Bloc] [bit] NOT NULL,
	[email_user] [nvarchar](200) NOT NULL,
	[telephone_user] [nvarchar](20) NOT NULL,
	[num_cin_user] [nvarchar](20) NOT NULL,
	[num_cnrps_user] [nvarchar](20) NOT NULL,
	[validation_technique] [bit] NOT NULL,
	[validation_finale] [bit] NOT NULL,
	[iscontrolleur] [bit] NOT NULL,
	[user_creation_compte] [nvarchar](20) NULL,
	[Cpt_MppsAvecTotale] [bit] NOT NULL,
	[token] [nvarchar](max) NULL,
	[email_professionnel] [nvarchar](200) NULL,
	[profil] [nvarchar](20) NOT NULL,
	[AdministrateurCaisse] [bit] NOT NULL,
	[hasched_password] [nvarchar](500) NOT NULL,
	[salt] [nvarchar](500) NOT NULL,
	[hash_iterations] [int] NOT NULL,
 CONSTRAINT [PK_Access Control] PRIMARY KEY NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ACCESS_FORM_USER]    Script Date: 10/03/2023 08:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCESS_FORM_USER](
	[Module] [nvarchar](3) NOT NULL,
	[User] [nvarchar](50) NOT NULL,
	[Form] [nvarchar](250) NOT NULL,
	[Control] [nvarchar](50) NOT NULL,
	[Visible] [bit] NOT NULL,
	[personalise] [bit] NOT NULL,
	[NumDeleg] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_ACCESS_FORM_USER] PRIMARY KEY CLUSTERED 
(
	[Module] ASC,
	[User] ASC,
	[Form] ASC,
	[Control] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ACCESS_MENU_USER]    Script Date: 10/03/2023 08:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCESS_MENU_USER](
	[Module] [nvarchar](3) NOT NULL,
	[User] [nvarchar](50) NOT NULL,
	[MENU] [nvarchar](15) NOT NULL,
	[VISIBLE] [bit] NOT NULL,
	[personalise] [bit] NOT NULL,
	[NumDeleg] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_ACCESS_MENU_USER] PRIMARY KEY CLUSTERED 
(
	[Module] ASC,
	[User] ASC,
	[MENU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Form]    Script Date: 10/03/2023 08:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Form](
	[Module] [nvarchar](3) NOT NULL,
	[Form] [nvarchar](250) NOT NULL,
	[NomForm] [nvarchar](250) NOT NULL,
	[Control] [nvarchar](50) NOT NULL,
	[NomControl] [nvarchar](250) NOT NULL,
	[code_menu] [nvarchar](250) NOT NULL,
	[ParentForm] [nvarchar](250) NULL,
	[ParentControl] [nvarchar](50) NULL,
	[logo] [nvarchar](220) NULL,
	[order_button] [nvarchar](1) NULL,
 CONSTRAINT [PK_Form] PRIMARY KEY CLUSTERED 
(
	[Module] ASC,
	[Form] ASC,
	[Control] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuP]    Script Date: 10/03/2023 08:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuP](
	[Module] [nvarchar](3) NOT NULL,
	[CodMnP] [nvarchar](15) NOT NULL,
	[DesMenuP] [nvarchar](120) NOT NULL,
	[MnName] [nvarchar](120) NOT NULL,
	[Code_Menu] [nvarchar](30) NULL,
	[logo] [nvarchar](255) NULL,
	[order] [int] NULL,
 CONSTRAINT [PK_MenuP] PRIMARY KEY CLUSTERED 
(
	[Module] ASC,
	[CodMnP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Matricule]  DEFAULT ('') FOR [Matricule]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Old_Grp_1]  DEFAULT ('') FOR [Old_Grp]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Actif__226272D8]  DEFAULT ((1)) FOR [Actif]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Actif1]  DEFAULT ((0)) FOR [Ch_Stat]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Valid_Cpt_Rend]  DEFAULT ((1)) FOR [Valid_Cpt_Rend]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Cleda__7B20EA8A]  DEFAULT ('') FOR [Cledallas]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__style__5FE3AFC0]  DEFAULT ((0)) FOR [styleClaire]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Compt__22466FFA]  DEFAULT ((0)) FOR [CompteExpire]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__expir__2522DCA5]  DEFAULT ((0)) FOR [expire]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__nbre___270B2517]  DEFAULT ((0)) FOR [nbre_jours_expiration]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__cptSh__49603D1B]  DEFAULT ((0)) FOR [cptShowAllPatient]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__cptco__4B48858D]  DEFAULT ((0)) FOR [cptconsultActivityAll]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__UserM__4742EA7F]  DEFAULT ('') FOR [UserModif]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Admin__492B32F1]  DEFAULT ((0)) FOR [Admin_Ev_Indesirable]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Valid_PH_Nuit]  DEFAULT ((1)) FOR [Valid_PH_Nuit]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_nature_user_DS]  DEFAULT ('') FOR [nature_user_DS]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_codePin]  DEFAULT ('1234') FOR [codePin]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Autor__3D1A61C7]  DEFAULT ((0)) FOR [Autorise_av]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [Valid_PH_Stupefiant]  DEFAULT ((1)) FOR [Valid_PH_Stupefiant]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Nature_1]  DEFAULT ('') FOR [NatBio]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Financier]  DEFAULT ((0)) FOR [Financier]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Type_user]  DEFAULT ('autre') FOR [Type_user]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_caissier]  DEFAULT ((0)) FOR [caissier]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF_Access Control_Autorise_securite]  DEFAULT ((0)) FOR [Autorise_securite]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Matri__7EB3E651]  DEFAULT ('') FOR [MatriculeResp]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Cont__mat__009C2EC3]  DEFAULT ('') FOR [mat]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__affic__243A70BB]  DEFAULT ((1)) FOR [afficheStatCpt]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Acces__67F07C8A]  DEFAULT ((0)) FOR [Access_Planning_actuelle]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Acces__68E4A0C3]  DEFAULT ((0)) FOR [Access_Planning_anterieur]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Acces__698F7790]  DEFAULT ((0)) FOR [Access_DAF]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Arran__1BB0E309]  DEFAULT ((0)) FOR [Arrangement]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [DF__Access Co__Rappr__11BD647B]  DEFAULT ((0)) FOR [Rapprochement_Mois_diff]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [CodeEmployer]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((1)) FOR [CptDictaphoneAuto]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [cpt_affichehistorique]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [cpt_afficheboutons]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [cpt_afficheAdicter]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [cpt_afficheAValider]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [cpt_afficheMedSeclionner]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [type_signature]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [signature_text]
GO
ALTER TABLE [dbo].[Access Control] ADD  CONSTRAINT [Valid_PH_Stupefiant_Bloc]  DEFAULT ((0)) FOR [Valid_PH_Stupefiant_Bloc]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [email_user]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [telephone_user]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [num_cin_user]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [num_cnrps_user]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [validation_technique]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [validation_finale]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [iscontrolleur]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [Cpt_MppsAvecTotale]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('Technicien') FOR [profil]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ((0)) FOR [AdministrateurCaisse]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [hasched_password]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [salt]
GO
ALTER TABLE [dbo].[Access Control] ADD  DEFAULT ('') FOR [hash_iterations]
GO
ALTER TABLE [dbo].[ACCESS_FORM_USER] ADD  CONSTRAINT [DF_ACCESS_FORM_USER_personalise]  DEFAULT ((0)) FOR [personalise]
GO
ALTER TABLE [dbo].[ACCESS_FORM_USER] ADD  DEFAULT ('') FOR [NumDeleg]
GO
ALTER TABLE [dbo].[ACCESS_MENU_USER] ADD  CONSTRAINT [DF_ACCESS_MENU_USER_personalise]  DEFAULT ((0)) FOR [personalise]
GO
ALTER TABLE [dbo].[ACCESS_MENU_USER] ADD  DEFAULT ('') FOR [NumDeleg]
GO
ALTER TABLE [dbo].[Form] ADD  CONSTRAINT [DF_Form_code_menu]  DEFAULT ('') FOR [code_menu]
GO
ALTER TABLE [dbo].[MenuP] ADD  CONSTRAINT [DF_MenuP_Code_Menu]  DEFAULT ('') FOR [Code_Menu]
GO
