import jenkins.model.Jenkins
import java.util.logging.Logger

def logger = Logger.getLogger("")
def isIntalled = false
def isInitialized = false

def pluginsList = "ws-cleanup timestamper credentials-biding build-timeout antisamy-markup-formatter cloudbees-folder pipeline-github-lib github-branch-source workflow-aggregator gradle ant mailer email-text ldap pam-auth matrix-auth ssh-slaves github git"

def plugins = pluginsList.split()

def masterJenkinsInstance = Jenkins.getInstance()
def pluginManager = masterJenkinsInstance.getPluginManager()
def updateCenter = masterJenkinsInstance.getUpdateCenter()

plugins.each {
    logger.info("Checking " + it)
    if (!pluginManager.getPlugin(it)) {
        logger.info("Looking update center for" + it)
        if (!isInitialized) {
            updateCenter.updateAllSites()
            isInitialized = true
        }
        def plugin = updateCenter.getPlugin(it)
        if (plugin) {
            logger.info("Installing " + it)
            def installFuture = plugin.deploy()
            while(!installFuture.isDone()) {
                logger.info("Waiting for plugin install: " + it)
                sleep(3000)
            }
            isIntalled = true
        }
    }
}

if (isIntalled) {
    logger.info("Plugin installed, restarting Jenkins!")
    masterJenkinsInstance.save()
    masterJenkinsInstance.restart()
}