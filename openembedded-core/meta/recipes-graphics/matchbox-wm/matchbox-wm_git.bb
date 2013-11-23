DESCRIPTION = "Matchbox window manager"
HOMEPAGE = "http://matchbox-project.org"
BUGTRACKER = "http://bugzilla.openedhand.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/wm.h;endline=21;md5=a7e844465edbcf79c282369f93caa835 \
                    file://src/main.c;endline=21;md5=3e5d9f832b527b0d72dbe8e3c4c60b95 \
                    file://src/wm.c;endline=21;md5=8dc9d24477d87ef5dfbc2e4927146aab"

SECTION = "x11/wm"
DEPENDS = "libmatchbox virtual/libx11 libxext libxrender startup-notification expat gconf"

SRCREV = "686178379e5ad00453d9f6f50b53777372a29e68"
PV = "1.2+git${SRCPV}"
PR = "r0"

SRC_URI = "git://git.yoctoproject.org/matchbox-window-manager;protocol=git \
           file://configure_fix.patch;maxrev=1818 \
           file://kbdconfig"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} = "${bindir}/* \
               ${datadir}/matchbox \
               ${sysconfdir}/matchbox \
               ${datadir}/themes/blondie/matchbox \
               ${datadir}/themes/Default/matchbox \
               ${datadir}/themes/MBOpus/matchbox"

EXTRA_OECONF = " --enable-startup-notification \
                 --disable-xrm \
                 --enable-expat \
                 --with-expat-lib=${STAGING_LIBDIR} \
                 --with-expat-includes=${STAGING_INCDIR}"

do_install_prepend() {
	install ${WORKDIR}/kbdconfig ${S}/data/kbdconfig
}