package species

import species.auth.SUser

abstract class NamesSorucedata extends Sourcedata {

    List<SUser> contributors;
	static hasMany = [contributors: SUser]
	def utilsService;
    static constraints = {
    
	}

    static mapping = {
        tablePerHierarchy false
        //tablePerSubClass true
    }
    
	def beforeInsert(){
		super.beforeInsert()
		if(!contributors){
			this.addToContributors(uploader)
		}
	}
	
	def beforeUpdate(){
		//overwriting base class method
	}
	
	def updateContributors(List<SUser> users){
		if(!users) return
		
//		if (!this.isAttached()) {
//			this.attach()
//		}
		
		users.minus(contributors)
		
		users.each { u ->
			this.addToContributors(u)
		}
		
		if(!save(flush:true)){
			this.errors.allErrors.each { log.error it }
		}
	}

    boolean isContributor(SUser user) {
        if(!user) user = springSecurityService.currentUser;
        return this.contributors.contains(user) || utilsService.isAdmin(user);
    }
}
