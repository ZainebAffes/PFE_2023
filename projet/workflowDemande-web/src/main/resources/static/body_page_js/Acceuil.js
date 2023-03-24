

$(function () {
    if (window.parent.$('#left-panel').attr('hided') === 'false') {
        window.parent.$('#hide-menu > span > a').click();
        window.parent.$('#left-panel').attr('hided', 'true');
    }
    var url = `${url_base_access}/gestion-access-core/api/access-menu-user/findAccessMenuUser?module=${idModule}`;

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


            $("#listModules ul").append(html + htmlSousMenu + htmlSousSousMenu);

            eventbtn();
        }
    });



});


function eventbtn() {

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

    $("#10").unbind("click");
    $("#10").bind("click", function (e) {
        ouvrirOnglet("Paramétrage des demandes", "ListeDemande", false, 'fils', '', 'ListeDemande');
    });

    $("#20").unbind("click");
    $("#20").bind("click", function (e) {
        ouvrirOnglet("Types des demandes", "TypeDemande", false, 'fils', '', 'TypeDemande');
    });
}









