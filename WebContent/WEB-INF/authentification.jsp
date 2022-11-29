

<%@include file="header.jsp" %>


 <main class="bg-white-300 flex-1 p-3 overflow-hidden">
  <div class="flex flex-1  flex-col md:flex-row lg:flex-row mx-2">
                        <div class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full">
                            <div class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b">
                                Connectez-vous
                            </div>
                            
                           
                            <div class="p-3">
                             <p class="text-red-700 font-bold font-md pb-2">${errors.error }</p>
                                <form method="post" class="w-full">
                            
                                        
                                     <div class="flex flex-wrap -mx-3 mb-6">
                                        <div class="w-full px-3">
                                            <label class="block uppercase tracking-wide text-grey-darker text-xs font-light mb-1"
                                                   for="grid-password">
                                                Login
                                            </label>
                                            <input class="appearance-none block w-full bg-grey-200 text-grey-darker border border-grey-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-grey"
                                                   name="login" id="grid-password" type="text" placeholder="login" value="${errors.login}">
                                          
                                        </div>
                                    </div>
                                     <div class="flex flex-wrap -mx-3 mb-6">
                                        <div class="w-full px-3">
                                            <label class="block uppercase tracking-wide text-grey-darker text-xs font-light mb-1"
                                                   for="grid-password">
                                               Mot de passe
                                            </label>
                                            <input class="appearance-none block w-full bg-grey-200 text-grey-darker border border-grey-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-grey"
                                                   name="pass" id="grid-password" type="password" placeholder="mot de pass">
                                          
                                        </div>
                                    </div>
                        
                             <input class="shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="submit" value="Se connecter" >
                                               
                                </form>
                            </div>
                        </div>
                    </div>
</main>