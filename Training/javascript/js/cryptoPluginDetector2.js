run();
function run()
    {
        var ProviderName = "Crypto-Pro GOST R 34.10-2001 Cryptographic Service Provider";
        var ProviderType = 75;

        var elem = document.getElementById("ProviderName");
        var ProviderName = elem.value;

        elem = document.getElementById("ProviderType");
        var ProviderType = elem.value;

        var Version = get_version(ProviderName, ProviderType);

        elem = document.getElementById("ProviderVersion");

        if(Version)
            elem.value = Version;
    }

    function get_version(ProviderName, ProviderType)
    {
        var oVersion;
        try
        {
            var oAbout = cadesplugin.CreateObject("CAdESCOM.About");

            oVersion= oAbout.CSPVersion(ProviderName, parseInt(ProviderType, 10));

            var Minor = oVersion.MinorVersion;
            var Major = oVersion.MajorVersion;
            var Build = oVersion.BuildVersion;
            var Version = oVersion.toString();

            return Version;
        }
        catch(er)
        {
            if(er.message.indexOf("0x80090019")+1)
                return "Указанный CSP не установлен";
            else
                return er.message;
            return false;
        }
    }
