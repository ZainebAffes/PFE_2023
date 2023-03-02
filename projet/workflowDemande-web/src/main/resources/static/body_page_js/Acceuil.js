
var menuNouveau = findParamByCode("Menu_nouveau");
menuNouveau = menuNouveau !== null ? menuNouveau.valeur : "0";

var authentificationWeb = findParamByCode("authentificationWeb");
//parmAuthentificationWeb = authentificationWeb !== null && authentificationWeb !== "" ? authentificationWeb.valeur.toUpperCase() : "";

parmAuthentificationWeb ="FALSE";
var urlAuthentificationWeb = findParamByCode("urlAuthentificationWeb");
parmurlAuthentificationWeb = urlAuthentificationWeb !== null && urlAuthentificationWeb !== "" ? urlAuthentificationWeb.valeur.toUpperCase() : "";
$(function () {
    if (window.parent.$('#left-panel').attr('hided') === 'false') {
        window.parent.$('#hide-menu > span > a').click();
        window.parent.$('#left-panel').attr('hided', 'true');
    }
    var url = `${url_base_access}/gestion-access-core/api/access-menu-user/findAccessMenuUser?module=${idModule}`;
    if (parmAuthentificationWeb === "TRUE") {
        var url = `${parmurlAuthentificationWeb}/api/AccessMenuUsers/AccessMenuUserByModule?username=` + window.localStorage.getItem('username') + `&module=${idModule}`;
    }
    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        async: false,
        headers: {
            'X-Auth-Token': localStorage.getItem("x-auth-token"),
            'Accept-Language': localStorage.getItem("langue")
        },
        error: function (jqXHR, textStatus, errorThrown) {
        },
        complete: function (jqXHR, textStatus) {

        },
        success: function (data, textStatus, jqXHR) {
            var html = '';
            var htmlSousMenu = '';
            var htmlSousSousMenu = '';
            var bouton = data;
            let boutonPrincipal = _.filter(bouton, function (item) {
                return  item.codeMenu.length === 2;
            });
            if (menuNouveau === '0') {
                for (let j = 0; j < boutonPrincipal.length; j++) {
                    let boutonSubMenu = _.filter(bouton, function (item) {
                        return item.codeMenu.length === 4 && item.codeMenu.indexOf(boutonPrincipal[j].codeMenu) === 0 && item.codeMenu !== boutonPrincipal[j].codeMenu;
                    });

                    if (boutonSubMenu.length > 0) {
                        html += `  <li class="tile purple w2 h1 subMenu"  id="${boutonPrincipal[j].codeMenu}">
                                   <a class="menu">
                                       <i class="fa ${boutonPrincipal[j].menu.logo} icon"></i>
                                       <p class="title">${boutonPrincipal[j].menu.designation}</p>
                                       <i class="fa fa-ellipsis-v ellipsis" aria-hidden="true"></i>
                                       <ul class="sous-menus">`;

                        for (let k = 0; k < boutonSubMenu.length; k++) {
                            let boutonSous_SubMenu = _.filter(bouton, function (item) {
                                return item.codeMenu.length === 6 && item.codeMenu.indexOf(boutonSubMenu[k].codeMenu) === 0 && item.codeMenu !== boutonSubMenu[k].codeMenu;
                            });
                            if (boutonSous_SubMenu.length > 0)
                            {
                                html += `  <li class="sous-menus_subMenu" id="${boutonSubMenu[k].codeMenu}">
                                       <i class="fa ${boutonSubMenu[k].menu.logo}"></i>
                                       <p>${boutonSubMenu[k].menu.designation}</p>
                                       <ul class="sous-menus">`;
                                for (let l = 0; l < boutonSous_SubMenu.length; l++)
                                {
                                    html += `<li  id="${boutonSous_SubMenu[l].codeMenu}" class="link"
                                       breadcrumb="${bouton[j].menu.designation} / ${boutonSous_SubMenu[l].menu.designation}">
                                       <i class="fa ${boutonSous_SubMenu[l].menu.logo}"></i>
                                       <p>${boutonSous_SubMenu[l].menu.designation}</p>
                                       </li>`;
                                }
                                html += `</ul> </li>`;
                            } else
                            {
                                html += `<li  id="${boutonSubMenu[k].codeMenu}" class="link"
                                       breadcrumb="${bouton[j].menu.designation} / ${boutonSubMenu[k].menu.designation}">
                                       <i class="fa ${boutonSubMenu[k].menu.logo}"></i>
                                       <p>${boutonSubMenu[k].menu.designation}</p>
                                         </li>`;
                            }
                        }

                        html += `</ul> </a></li>`;
                    } else {
                        html += `<li class="tile w2 h1 subMenu">
                                   <a  id="${boutonPrincipal[j].codeMenu}" class="link" breadcrumb="${boutonPrincipal[j].menu.designation}">
                                       <i class="fa ${boutonPrincipal[j].menu.logo} icon"></i>
                                       <p class="title">${boutonPrincipal[j].menu.designation}</p>
                                       <i class="fa fa-ellipsis-v ellipsis" aria-hidden="true"></i>
                                   </a>
                                   </li> `;
                    }
                }
            } else {
                for (let j = 0; j < boutonPrincipal.length; j++) {
                    let boutonSubMenu = _.filter(bouton, function (item) {
                        return item.codeMenu.length === 4 && item.codeMenu.indexOf(boutonPrincipal[j].codeMenu) === 0 && item.codeMenu !== boutonPrincipal[j].codeMenu;
                    });

                    html += `  <li class="tile purple w2 h1 subMenu principal"  id="${boutonPrincipal[j].codeMenu}" >
                                   <a class="menu">
                                       <i class="fa ${boutonPrincipal[j].menu.logo} icon"></i>
                                       <p class="title">${boutonPrincipal[j].menu.designation}</p></a></li>`;

                    for (let k = 0; k < boutonSubMenu.length; k++) {
                        let boutonSous_SubMenu = _.filter(bouton, function (item) {
                            return item.codeMenu.length === 6 && item.codeMenu.indexOf(boutonSubMenu[k].codeMenu) === 0 && item.codeMenu !== boutonSubMenu[k].codeMenu;
                        });
                        htmlSousMenu += `  <li class="tile purple w2 h1 subMenu ${boutonPrincipal[j].codeMenu}" id="${boutonSubMenu[k].codeMenu}" 
                                    breadcrumb="${boutonPrincipal[j].menu.designation}" style="display:none">
                                       <a class="menu"><i class="fa ${boutonSubMenu[k].menu.logo} icon"></i>
                                       <p  class="title">${boutonSubMenu[k].menu.designation}</p></a>
                                       </li>`;
                        for (let l = 0; l < boutonSous_SubMenu.length; l++)
                        {
                            htmlSousSousMenu += `<li  id="${boutonSous_SubMenu[l].codeMenu}" class="tile purple w2 h1 subMenu ${boutonSubMenu[k].codeMenu}"
                                       breadcrumb="${bouton[j].menu.designation} / ${boutonSous_SubMenu[l].menu.designation}" style="display:none">
                                      <a class="menu"> <i class="fa ${boutonSous_SubMenu[l].menu.logo} icon"></i>
                                       <p  class="title">${boutonSous_SubMenu[l].menu.designation}</p></a>
                                       </li>`;
                        }
                    }
                }
            }

            $("#listModules ul").append(html + htmlSousMenu + htmlSousSousMenu);

            eventbtn();
        }
    });


   
});


function eventbtn() {
    if (menuNouveau === '0') {
        $('.tile.subMenu').mouseenter(function () {
            $('.sous-menus_subMenu > ul').hide().removeClass("left").removeClass("right");
        });

        $(".sous-menus > :not(.sous-menus_subMenu)").mouseenter(function () {
            $(this).closest("ul").find('.sous-menus_subMenu > ul').hide().removeClass("left").removeClass("right");
        });

        $(".sous-menus > .sous-menus_subMenu").mouseenter(function () {
            $(this).closest("ul").find('ul').hide().removeClass("left").removeClass("right");
            let windowWidth = $(window).width();
            let leftContainer = $(this).closest("ul").offset().left + $(this).closest("ul").width();
            let widthUl = $(this).find("ul:eq(0)").width();
            if (leftContainer + widthUl <= windowWidth) {
                $(this).find("ul:eq(0)").addClass("right");
            } else {
                $(this).find("ul:eq(0)").addClass("left");
            }
            $(this).find("ul:eq(0)").fadeIn();
        });
    } else {
        $('.tile.subMenu').click(function () {
            var id = $(this).attr('id');
            var title = $(this).find('.title').html();
            $('.tile.subMenu').css('display', 'none');
            $('.subMenu.' + id).css('display', '');
            $(".module").append("<a parent='" + id + "'><p>" + title + "</p></a>");
            $(".module a").off('click').on('click', function () {
                var parent = $(this).attr('parent');
                $('.tile.subMenu').css('display', 'none');
                $('.subMenu.' + parent).css('display', '');
                $(this).nextAll().remove();
            });
        });
    }
    $("#10").unbind("click");
    $("#10").bind("click", function (e) {
        ouvrirOnglet("Liste des patients", "ListePatient", false, 'fils', '', 'ListePatient');
    });
}









