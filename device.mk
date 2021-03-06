#
# Copyright (C) 2016 The Sayanogen Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

$(call inherit-product, $(SRC_TARGET_DIR)/product/languages_full.mk)

# Include atv base
$(call inherit-product, device/google/atv/products/atv_base.mk)

# Get non-open-source specific aspects for pearlyn
$(call inherit-product-if-exists, vendor/razer/pearlyn/pearlyn-vendor.mk)

# Overlays
DEVICE_PACKAGE_OVERLAYS += device/razer/pearlyn/overlay 

PRODUCT_AAPT_CONFIG := normal large xlarge hdpi xhdpi
PRODUCT_AAPT_PREF_CONFIG := xhdpi
PRODUCT_CHARACTERISTICS := nosdcard,tv

$(call inherit-product, frameworks/native/build/phone-xhdpi-1024-dalvik-heap.mk)

$(call inherit-product-if-exists, frameworks/native/build/phone-xxhdpi-2048-hwui-memory.mk)

# /etc files
PRODUCT_COPY_FILES += $(call find-copy-subdir-files,*,$(LOCAL_PATH)/etc,system/etc) 

# Keylayout files
PRODUCT_COPY_FILES += $(call find-copy-subdir-files,*,$(LOCAL_PATH)/keylayout,system/usr/keylayout) 

# Let's put adb insecure on boot, for debug purposes	
ADDITIONAL_DEFAULT_PROPERTIES += \
    ro.adb.secure=0 \
	ro.secure=0 \
	ro.debuggable=1 \
	ro.hardware=qcom \
	persist.sys.usb.config=mtp
	
# Rootdir
PRODUCT_PACKAGES += \
    fstab.qcom \
    init.class_main.sh \
    init.mdm.sh \
    init.qcom.class_core.sh \
    init.pearlyn.diag.rc \
    init.qcom.early_boot.sh \
    init.qcom.factory.sh \
    init.qcom.rc \
    init.qcom.sh \
    init.qcom.ssr.sh \
    init.qcom.syspart_fixup.sh \
    init.razer.info.sh \
    init.razer.peripherals.sh \
    init.razer.ping.sh \
    init.razer.rc \
    init.target.rc \
    init.razer.usb.rc \
    init.razer.usb.sh \
    ueventd.qcom.rc

# Media
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/media/mixer_paths.xml:system/etc/mixer_paths.xml \
    $(LOCAL_PATH)/media/audio_policy.conf:system/etc/audio_policy.conf \
    $(LOCAL_PATH)/media/media_codecs.xml:system/etc/media_codecs.xml \
    $(LOCAL_PATH)/media/media_profiles.xml:system/etc/media_profiles.xml \
    $(LOCAL_PATH)/media/media_codecs_performance.xml:system/etc/media_codecs_performance.xml \
    $(LOCAL_PATH)/media/audio_platform_info.xml:system/etc/audio_platform_info.xml \
    $(LOCAL_PATH)/media/audio_platform_info_i2s.xml:system/etc/audio_platform_info_i2s.xml \
    $(LOCAL_PATH)/media/listen_platform_info.xml:system/etc/listen_platform_info.xml \
    $(LOCAL_PATH)/media/media_codecs_google_audio.xml:system/etc/media_codecs_google_audio.xml \
    $(LOCAL_PATH)/media/media_codecs_google_telephony.xml:system/etc/media_codecs_google_telephony.xml \
    $(LOCAL_PATH)/media/media_codecs_google_video.xml:system/etc/media_codecs_google_video.xml\
    $(LOCAL_PATH)/media/mixer_paths_i2s.xml:system/etc/mixer_paths_i2s.xml \
    $(LOCAL_PATH)/media/sound_trigger_mixer_paths.xml:system/etc/sound_trigger_mixer_paths.xml \
    $(LOCAL_PATH)/media/sound_trigger_platform_info.xml:system/etc/sound_trigger_platform_info.xml
    
# Bootanimation    
PRODUCT_COPY_FILES += $(LOCAL_PATH)/media/bootanimation.zip:system/media/bootanimation.zip \
    
# Fonts fix
PRODUCT_COPY_FILES += \
    frameworks/base/data/fonts/DroidSansFallback.ttf:system/fonts/DroidSansFallback.ttf \
    frameworks/base/data/fonts/MTLmr3m.ttf:system/fonts/MTLmr3m.ttf 
    
# Bluetooth
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/bluetooth/auto_pair_devlist.conf:system/etc/auto_pair_devlist.conf \
    $(LOCAL_PATH)/bluetooth/bt_did.conf:system/etc/bt_did.conf \
    $(LOCAL_PATH)/bluetooth/bt_stack.conf:system/etc/bt_stack.conf 
    
# Sys Init
PRODUCT_PACKAGES += \
    hcidump.sh \
    hsic.control.bt.sh \
    init.ath3k.bt.sh \
    init.crda.sh \
    init.qcom.audio.sh \
    init.qcom.bt.sh \
    init.qcom.coex.sh \
    init.qcom.efs.sync.sh \
    init.qcom.fm.sh \
    init.qcom.modem_links.sh \
    init.qcom.post_boot.sh \
    init.qcom.sdio.sh \
    init.qcom.wifi.sh \
    qca6234-service.sh \
    usf_post_boot.sh \
    sec_config \
    valiService.conf\
    vold.fstab \
    usf_post_boot.sh
	
# Busybox & ntfs-3g
PRODUCT_PACKAGES += \
	busybox \
	ntfs-3g
	
# Launcher Mod Apps (thanks to CM13 for fugu)
PRODUCT_PACKAGES += \
	ForgeHub \
	AppDrawer	

# Permissions
PRODUCT_COPY_FILES += \
    frameworks/native/data/etc/android.hardware.audio.low_latency.xml:system/etc/permissions/android.hardware.audio.low_latency.xml \
    frameworks/native/data/etc/android.hardware.hdmi.cec.xml:system/etc/permissions/android.hardware.hdmi.cec.xml \
    frameworks/native/data/etc/android.software.device_admin.xml:system/etc/permissions/android.software.device_admin.xml \
    frameworks/native/data/etc/android.software.managed_users.xml:system/etc/permissions/android.software.managed_users.xml \
    frameworks/native/data/etc/android.hardware.bluetooth_le.xml:system/etc/permissions/android.hardware.bluetooth_le.xml \
    frameworks/native/data/etc/android.hardware.bluetooth.xml:system/etc/permissions/android.hardware.bluetooth.xml \
    frameworks/native/data/etc/android.hardware.usb.host.xml:system/etc/permissions/android.hardware.usb.host.xml \
    frameworks/native/data/etc/android.hardware.wifi.xml:system/etc/permissions/android.hardware.wifi.xml \
    frameworks/native/data/etc/android.hardware.wifi.direct.xml:system/etc/permissions/android.hardware.wifi.direct.xml \
    frameworks/native/data/etc/android.software.midi.xml:system/etc/permissions/android.software.midi.xml 

# Build.prop overrides
PRODUCT_PROPERTY_OVERRIDES += \
	ro.product.board=apq8084 \
	net.bt.name=Forge \
	dalvik.vm.dex2oat-swap=false \
	ro.com.google.clientidbase=android-pearlyn
	
# Power HAL
PRODUCT_PACKAGES += \
    power.apq8084

# tcmiface for tcm support
PRODUCT_PACKAGES += tcmiface
PRODUCT_BOOT_JARS += tcmiface
    
# Audio
PRODUCT_PACKAGES += \
    audio.r_submix.default \
    audio.usb.default \
    audio.a2dp.default       
    
# Keystore
PRODUCT_PACKAGES += \
    keystore.apq8084        
    
# Without this filter, we get very close to the limit.
PRODUCT_DEX_PREOPT_DEFAULT_FLAGS += --compiler-filter=space     

# Bootanimation
TARGET_BOOTANIMATION_MULTITHREAD_DECODE := true
TARGET_BOOTANIMATION_PRELOAD := true
TARGET_BOOTANIMATION_TEXTURE_CACHE := true
    
#twrp
PRODUCT_COPY_FILES += $(LOCAL_PATH)/twrp/twrp.fstab:recovery/root/etc/twrp.fstab    
