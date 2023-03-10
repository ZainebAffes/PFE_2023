
insert into Menup  (CodMnP,Module, MnName,logo,DesMenuP,[order]) values ('10','WOR','Workflow demandes','fas fa-cogs','Paramétrage des demandes',1)
INSERT INTO [ACCESS_MENU_user]( MENU,Module,[user], VISIBLE) VALUES('10','WOR','admin',1)
insert into Menup  (CodMnP,Module, MnName,logo,DesMenuP,[order]) values ('20','WOR','Workflow demandes','fas fa-tasks','Types des demandes',2)
INSERT INTO [ACCESS_MENU_user]( MENU,Module,[user], VISIBLE) VALUES('20','WOR','admin',1)
------//****** table FORM
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Ajouter', N'Ajouter', N'10', NULL, NULL,'btn-success glyphicon-plus','1')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Modifier', N'Modifier', N'10', NULL, NULL,'btn-success glyphicon-plus','2')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Annuler', N'Annuler', N'10', NULL, NULL,'btn-success glyphicon-plus','3')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Consulter', N'Consulter', N'10', NULL, NULL,'btn-warning glyphicon-list','4')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Imprimer', N'Imprimer', N'10', NULL,  NULL,'btn-default glyphicon-print','5')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_ParametragedesDemandes', N'ParametragedesDemandes', N'btn_Exporter', N'Exporter', N'10', NULL,  NULL,'btn-default glyphicon-print','6')
-------ajout de l'acces pour admin
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Ajouter', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Modifier', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Annuler', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Imprimer', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Consulter', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_ParametragedesDemandes', N'btn_Exporter', 1, 0, N'')

------//****** table FORM
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Ajouter', N'Ajouter', N'20', NULL, NULL,'btn-success glyphicon-plus','1')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Modifier', N'Modifier', N'20', NULL, NULL,'btn-success glyphicon-plus','2')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Annuler', N'Annuler', N'20', NULL, NULL,'btn-success glyphicon-plus','3')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Consulter', N'Consulter', N'20', NULL, NULL,'btn-warning glyphicon-list','4')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Imprimer', N'Imprimer', N'20', NULL,  NULL,'btn-default glyphicon-print','5')
INSERT [dbo].[Form] ([Module], [Form], [NomForm], [Control], [NomControl], [code_menu], [ParentForm], [ParentControl],logo,order_button) VALUES (N'WOR', N'Frm_TypesdesDemandes', N'TypesdesDemandes', N'btn_Exporter', N'Exporter', N'20', NULL,  NULL,'btn-default glyphicon-print','6')
-------ajout de l'acces pour admin
INSERT [dbo].[ACCESS_MENU_USER] ([Module], [User], [MENU], [VISIBLE], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'20', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Ajouter', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Modifier', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Annuler', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Imprimer', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Consulter', 1, 0, N'')
INSERT [dbo].[ACCESS_FORM_USER] ([Module], [User], [Form], [Control], [Visible], [personalise], [NumDeleg]) VALUES (N'WOR', N'admin', N'Frm_TypesdesDemandes', N'btn_Exporter', 1, 0, N'')



