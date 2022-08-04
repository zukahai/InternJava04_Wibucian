"use strict";

// Class definition
var KTThemeMode = function () {
	var menu;
	var callbacks = [];
	var the = this;
	var element;

    var getParamName = function(postfix) {
        var name = document.body.hasAttribute("data-kt-name") ? document.body.getAttribute("data-kt-name") + "_" : "";

        return "kt_" + name + "theme_mode" + "_" + postfix;
    }

    var getMode = function() {
        var modeParam = getParamName("value");
		var menuMode = getMenuMode();

        if ( localStorage.getItem(modeParam) !== null ) {
            return localStorage.getItem(modeParam);
        }

        if ( element.hasAttribute("data-theme") ) {
            return element.getAttribute("data-theme");
        }

		if ( menuMode ) {
			if ( menuMode === "system" ) {
				return getSystemMode();
			} else {
				return menuMode;
			}
        }

        return "light";
    }

    var setMode = function(mode, menuMode) {
		// Check input values
		if ( mode !== "light" && mode !== "dark" ) {
			return;
		}

		// Get param names
        var modeParam = getParamName("value");
		var menuModeParam = getParamName("menu");

		// Reset mode if system mode was changed
		if ( menuMode === 'system') {
			if ( getSystemMode() !==  mode ) {
				mode = getSystemMode();
			}
		}
		
		// Check menu mode
		if ( !menuMode) {
			menuMode = mode;
		}

		// Read active menu mode value
		var activeMenuItem = menu ? menu.querySelector('[data-kt-element="mode"][data-kt-value="' + menuMode + '"]') : null;

		// Enable switching state
		element.setAttribute("data-kt-theme-mode-switching", "true");
		
		// Set mode to the target element
		element.setAttribute("data-theme", mode);

		// Disable switching state
		setTimeout(function() {
			element.removeAttribute("data-kt-theme-mode-switching");
		}, 300);
		
		// Store mode value in storage
        localStorage.setItem(modeParam, mode);			
		
		// Set active menu item
		if ( activeMenuItem ) {
			localStorage.setItem(menuModeParam, menuMode);
			setActiveMenuItem(activeMenuItem);
		}			
    }

	var getMenuMode = function() {
		var menuModeParam = getParamName("menu");
		var menuItem = menu ? menu.querySelector('.active[data-kt-element="mode"]') : null;

		if ( menuItem && menuItem.getAttribute('data-kt-value') ) {
            return menuItem.getAttribute('data-kt-value');
        }

		if ( localStorage.getItem(menuModeParam) !== null ) {
			return localStorage.getItem(menuModeParam);
		}

		return "";
	}

	var getSystemMode = function() {
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? "dark" : "light";
    }

	var initMode = function() {
		setMode(getMode(), getMenuMode());
		KTEventHandler.trigger(element, 'kt.thememode.init', the);
	}

	var getActiveMenuItem = function() {
		return menu.querySelector('[data-kt-element="mode"][data-kt-value="' + getMenuMode() + '"]');
	}

	var setActiveMenuItem = function(item) {
		var menuModeParam = getParamName("menu");
		var menuMode = item.getAttribute("data-kt-value");
		
		var activeItem = menu.querySelector('.active[data-kt-element="mode"]');

		if ( activeItem ) {
			activeItem.classList.remove("active");
		}

		item.classList.add("active");
		localStorage.setItem(menuModeParam, menuMode);
	}

	var handleMenu = function() {
		var param = getParamName("menu");

		var items = [].slice.call(menu.querySelectorAll('[data-kt-element="mode"]'));

        items.map(function (item) {
            item.addEventListener("click", function(e) {
				e.preventDefault();

				var menuMode = item.getAttribute("data-kt-value");
				var mode = menuMode;

				if ( menuMode === "system") {
					mode = getSystemMode();
				} 		

				setMode(mode, menuMode);

				KTEventHandler.trigger(element, 'kt.thememode.change', the);
			});			     
        });
	}

    return {
        init: function () {
			menu = document.querySelector('[data-kt-element="theme-mode-menu"]');
			element = document.documentElement;

            initMode();

			if (menu) {
				handleMenu();
			}			
        },

        getMode: function () {
            return getMode();
        },

		getMenuMode: function() {
			return getMenuMode();
		},

		getSystemMode: function () {
            return getSystemMode();
        },

        setMode: function(mode) {
            return setMode(mode)
        },

		on: function(name, handler) {
			return KTEventHandler.on(element, name, handler);
		},

		off: function(name, handlerId) {
			return KTEventHandler.off(element, name, handlerId);
		}
    };
}();

// Initialize app on document ready
KTUtil.onDOMContentLoaded(function () {
    KTThemeMode.init();
});

// Declare KTThemeMode for Webpack support
if (typeof module !== 'undefined' && typeof module.exports !== 'undefined') {
    module.exports = KTThemeMode;
}